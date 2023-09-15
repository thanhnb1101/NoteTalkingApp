import { Injectable } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { HttpHeaders, HttpParams } from '@angular/common/http';

export interface iHttpOptions {
    headers?: HttpHeaders | {
        [header: string]: string | string[];
    };
    observe: 'body';
    params?: HttpParams | {
        [param: string]: string | string[];
    };
    reportProgress?: boolean;
    responseType?: 'json';
    withCredentials?: boolean;
}

@Injectable()
export class HttpOptionUtilService {

    constructor( 
            private router: Router, 
        ) {
    }

    public getOptions(): iHttpOptions {
        let headers = new HttpHeaders( { 'Content-Type': 'application/json' } );
        headers = headers.append( 'Authorization', 'Bearer ' + sessionStorage.getItem( 'access_token' ) );
        const options = {
            headers: headers,
            params: {},
            observe: 'body',
            reportProgress: true,
            responseType: 'json',
            withCredentials: false,
        };

        return <iHttpOptions>options;
    }

    public getOptionsWithParams( params: any ): iHttpOptions {
        let headers = new HttpHeaders( { 'Content-Type': 'application/json' } );
        headers = headers.append( 'Authorization', 'Bearer ' + sessionStorage.getItem( 'access_token' ) );
        const options = {
            headers: headers,
            params: params,
            observe: 'body',
            reportProgress: true,
            responseType: 'json',
            withCredentials: false,
        };

        return <iHttpOptions>options;
    }

    public getOptionsForUploadFile(): iHttpOptions {
        let headers = new HttpHeaders();
        headers = headers.append( 'Authorization', 'Bearer ' + sessionStorage.getItem( 'access_token' ) );
        const options = {
            headers: headers,
            params: {},
            observe: 'body',
            reportProgress: true,
            responseType: 'json',
            withCredentials: false,
        };

        return <iHttpOptions>options;
    }

    public getOptionsForDownloadFile(): iHttpOptions {
        let headers = new HttpHeaders( { 'Content-Type': 'application/json' } );
        headers = headers.append( 'Authorization', 'Bearer ' + sessionStorage.getItem( 'access_token' ) );

        const options = {
            headers: headers,
            params: {},
            observe: 'response',
            reportProgress: true,
            responseType: 'blob',
            withCredentials: false,
        };

        return <iHttpOptions>options;
    }
}
