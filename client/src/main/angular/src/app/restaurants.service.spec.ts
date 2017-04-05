import {TestBed, inject} from "@angular/core/testing";
import {RestaurantsService} from "./restaurants.service";
import {HttpModule, Http, BaseRequestOptions, XHRBackend} from "@angular/http";
import {MockBackend} from "@angular/http/testing";

describe('RestaurantsService', () => {
    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [RestaurantsService, {provide: Http, useClass: MockBackend }]
        });
    });

    it('should ...', inject([RestaurantsService], (service: RestaurantsService) => {
        expect(service).toBeTruthy();
    }));
});
