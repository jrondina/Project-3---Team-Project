package com.jonathan.james.eric.project_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SectionPageAdapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_page_adapter);

        //ToDo add code for setting up Fragment Pager Adapter

        APIServices apiservice = new APIServices();


        /* IGNORE THIS, DEBUG STUFF
        apiservice.articleSearch("harambe", apiservice.retrofitInit(this));
        apiservice.topNews("home",apiservice.retrofitInit(this));
        */
    }
}
