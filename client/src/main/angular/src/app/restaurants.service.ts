import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import "rxjs/Rx";
import "rxjs/add/operator/toPromise";

@Injectable()
export class RestaurantsService {

    private restaurantsUrl = "/restaurants"

    constructor(private http: Http) {
    }

    getRestaurantsHttp() {
        return this.http.get(this.restaurantsUrl).toPromise().then(response => response.json());
    }

    getEvents() {
        return this.http.get("/rest/events") ;
    }

}
