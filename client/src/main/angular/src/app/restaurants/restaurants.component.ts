import {Component, OnInit} from "@angular/core";
import {RestaurantsService} from "../restaurants.service";

@Component({
    selector: 'app-restaurants',
    templateUrl: './restaurants.component.html',
    styleUrls: ['./restaurants.component.css']
})
export class RestaurantsComponent implements OnInit {

    restaurants: Array<any>;

    constructor(private restaurantsSrvc: RestaurantsService) {}

    ngOnInit() {
        this.restaurantsSrvc.getRestaurantsHttp()
            .then(result => this.restaurants = result.map((item)=>{return item}))
            .catch(result => {console.log(result)});

        // this.restaurantsSrvc.getEvents().subscribe({next: (data) => console.log(data)});


    }


    onClick(restaurant) {
        console.log(`Restaurant clicked`);
        console.log(restaurant);
        restaurant.count++;
    }
}
