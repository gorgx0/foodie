import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {RestaurantsComponent} from "./restaurants/restaurants.component";
import {RestaurantsService} from "./restaurants.service";

@NgModule({
  declarations: [
    RestaurantsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [RestaurantsService],
  bootstrap: [RestaurantsComponent]
})
export class AppModule { }
