import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpEventType } from '@angular/common/http';
import { ArrayType } from '@angular/compiler';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

import { AppService } from './app.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'ImageUpload';
  validImageFormats=['jpeg','jpg']
  selectedFile : File;
  message: string
  show: boolean = false
  fileName: string;
  images=[]
  base: string = "http://localhost:8080/api"

  constructor(private httpClient: HttpClient, private appService : AppService) { }

  ngOnInit()
  {
      this.initGetImages()
  }

  initGetImages()
  {
    var url = this.base+"/image/getall"
    this.appService.getAllImages(url).subscribe(data => {
      data.forEach(element => {
          this.images.push(element.url)
      });
    })
  }
   onUpload() {
      var url = this.base + "/storage/upload"
      const uploadImageData = new FormData()
      uploadImageData.append('file',this.selectedFile)
      if(this.fileName!=null)
        uploadImageData.append('name',this.fileName)
      
      this.httpClient
      .post(url,uploadImageData)
      .subscribe((response) => {
      console.log(response)
      this.initGetImages();
      }
      );

   }

   onFileChanged(event) {
      this.selectedFile=event.target.files[0];
   }

   onNameChanged(event) {
     this.fileName = event.target.value;
   }
}
