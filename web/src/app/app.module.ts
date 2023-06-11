import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HighlightModule, HIGHLIGHT_OPTIONS } from 'ngx-highlightjs';
import {HighlightJsDirective} from "ngx-highlight-js";
import {FormsModule} from "@angular/forms";
import { HomeComponent } from './components/home/home.component';
import { PastedComponent } from './components/pasted/pasted.component';
import {RouterModule, Routes} from "@angular/router";
import {HttpClientModule} from "@angular/common/http";
import { AdminComponent } from './components/admin/admin.component';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { HeaderComponent } from './components/utility/header/header.component';
import { FooterComponent } from './components/utility/footer/footer.component';
import { AccountComponent } from './components/account/account.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: ":id",
    component: PastedComponent
  },
  {
    path: "account",
    component: AccountComponent
  },
  {
    path: '**',
    redirectTo: '',
    pathMatch: 'full'
  },
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    PastedComponent,
    AdminComponent,
    LoginComponent,
    RegisterComponent,
    HeaderComponent,
    FooterComponent,
    AccountComponent,
  ],
  imports: [
    BrowserModule,
    HighlightModule,
    HighlightJsDirective,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
  ],
  providers: [
    {
      provide: HIGHLIGHT_OPTIONS,
      useValue: {
        fullLibraryLoader: () => import('highlight.js'),
      }
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
