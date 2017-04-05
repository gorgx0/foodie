import {async, ComponentFixture, TestBed, fakeAsync, inject} from "@angular/core/testing";
import {RestaurantsComponent} from "./restaurants.component";
import {RestaurantsService} from "../restaurants.service";
import {MockBackend, MockConnection} from "@angular/http/testing";
import {HttpModule, Http, BaseRequestOptions, XHRBackend, Response, ResponseOptions} from "@angular/http";


describe('RestaurantsComponent', () => {
    let component: RestaurantsComponent;
    let fixture: ComponentFixture<RestaurantsComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [RestaurantsComponent],
            providers: [RestaurantsService,{provide: Http, useClass: MockBackend }]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(RestaurantsComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    // it('should create', () => {
    //     expect(component).toBeTruthy();
    // });

    it('should use mock',inject([MockBackend],(mockHttp: MockBackend)=>{
        mockHttp.connections.subscribe((connection: MockConnection) => {
            connection.mockRespond(new Response(new ResponseOptions({body: ''})));
        });
        component.restaurants
    }));
});
