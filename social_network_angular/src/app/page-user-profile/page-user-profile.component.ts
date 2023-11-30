import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-page-user-profile',
  templateUrl: './page-user-profile.component.html',
  styleUrls: ['./page-user-profile.component.css']
})
export class PageUserProfileComponent implements OnInit {
    constructor(private route: ActivatedRoute, private router: Router){}

    public userId: number = 0;

    ngOnInit(): void {
      this.route.queryParams.subscribe(params => {
        const data = params['userId'];

        this.userId = data
      });
    }
}
