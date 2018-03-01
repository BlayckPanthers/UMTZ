package com.ingesup.docblayck.umtz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.ingesup.docblayck.umtz.Dao.UserDao;
import com.ingesup.docblayck.umtz.Global.GlobalData;

/**
 * Created by Najib on 01/03/2018.
 */

public class PDFViewActivity extends AppCompatActivity {
    PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_viewer_layout);
        pdfView=(PDFView)findViewById(R.id.pdfView);
        pdfView.fromAsset("DocumentationCentreon.pdf").load();
    }
}