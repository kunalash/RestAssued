package com.demo.API.Tests.Site;

import com.demo.Helpers.Site.SiteServiceHelper;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestGETSite {
    SiteServiceHelper siteServiceHelper;
    @BeforeClass
    public void init(){
        siteServiceHelper = new SiteServiceHelper();
    }

    @Test
    public void testGETMeasureType(){
        final Response allMeasureType = siteServiceHelper.getAllMeasureType();
        allMeasureType.then().log().ifValidationFails().assertThat().statusCode(HttpStatus.SC_OK);

    }

    @Test
    public void testGETSites(){
        final Response allMeasureType = siteServiceHelper.getAllSites();
        allMeasureType.then().log().all().assertThat().statusCode(HttpStatus.SC_OK);
    }
}


