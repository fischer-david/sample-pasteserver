import { Component } from '@angular/core';
import {PasteService} from "../../services/paste.service";

@Component({
  selector: 'app-default',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  constructor(private pasteService: PasteService) {

  }

  postPaste() {
    const value = document.getElementById("value");

    if(value) {
      this.pasteService.createPaste(value.innerText).subscribe(x => {
        document.location.href = `/${x}`
      })
    }
  }
}
