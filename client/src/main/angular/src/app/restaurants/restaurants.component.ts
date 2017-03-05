import {Component, OnInit} from "@angular/core";
import {RestaurantsService} from "../restaurants.service";

@Component({
    selector: 'app-restaurants',
    templateUrl: './restaurants.component.html',
    styleUrls: ['./restaurants.component.css']
})
export class RestaurantsComponent implements OnInit {

    restaurants: Array<any>;

    constructor(private restaurantsSrvc: RestaurantsService) {
        // this.restaurants = restaurantsSrvc.getRestaurants();
        restaurantsSrvc.getRestaurantsHttp().then(result => this.restaurants = result);
    }

    ngOnInit() {
    }

}
