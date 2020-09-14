import { Component } from '@angular/core';
import { HttpClient, HttpEventType } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'ImageUpload';
  validImageFormats=['jpeg','jpg']
  selectedFile : File;
  message: string
  show: boolean = false

  constructor(private httpClient: HttpClient) { }
   onUpload() {
      var url = "http://localhost:8080/api/storage/upload"
      const uploadImage = new FormData()
      uploadImage.append('file',this.selectedFile,this.selectedFile.name)
      this.message = 'Image not uploaded successfully';
      this.httpClient
      .post(url,uploadImage, { observe: 'response'})
      .subscribe((response) => {
        if (response.status === 200) {
          this.message = 'Image uploaded successfully';
        } else {
          this.message = 'Image not uploaded successfully';
        }
      console.log(this.message)

      }
      );

   }

   onFileChanged(event) {
      this.selectedFile=event.target.files[0];
   }
}
