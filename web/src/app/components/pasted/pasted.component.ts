import {AfterViewInit, Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {combineLatest, map, Observable, of} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Paste} from "../../models/paste.model";
import {PasteService} from "../../services/paste.service";

@Component({
  selector: 'app-pasted',
  templateUrl: './pasted.component.html',
  styleUrls: ['./pasted.component.css']
})
export class PastedComponent {

  constructor(private pasteService: PasteService, protected httpClient: HttpClient, protected activatedRoute: ActivatedRoute, protected router: Router) {
    this.activatedRoute.params.subscribe(x => {
      const id = x["id"];
      pasteService.getPaste(id).subscribe(x => {
        if(x) {
          const element = document.getElementById("pasted");
          if(element) {
            if(x && x.value) {
              element.innerHTML = x.value
            } else {
              document.location.href = "/"
            }
          }
        } else {
          console.log(x)
        }
      })
    })
  }
}
