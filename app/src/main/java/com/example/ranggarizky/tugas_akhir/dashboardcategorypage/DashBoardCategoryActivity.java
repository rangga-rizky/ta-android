package com.example.ranggarizky.tugas_akhir.dashboardcategorypage;

import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ranggarizky.tugas_akhir.R;
import com.example.ranggarizky.tugas_akhir.categorypage.CategoryPresenter;
import com.example.ranggarizky.tugas_akhir.categorypage.CategoryRecyclerViewAdapter;
import com.example.ranggarizky.tugas_akhir.model.Category;
import com.example.ranggarizky.tugas_akhir.model.FreqData;
import com.example.ranggarizky.tugas_akhir.model.TopWords;
import com.example.ranggarizky.tugas_akhir.utils.SessionManager;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashBoardCategoryActivity extends AppCompatActivity implements DashboardCategoryView,HorizCategoryRecyclerViewAdapter.OnItemClicked {
    DashboardCategoryPresenter presenter;
    SessionManager sessionManager;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.lineChart)
    LineChart lineChart;
    @BindView(R.id.txtCategory)
    TextView txtCategory;
    @BindView(R.id.webView)
    WebView webView;
    ArrayList<String> wordCloud;
    private List<Category> categories = new ArrayList<>();
    private HorizCategoryRecyclerViewAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_category);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(this);
        initPresenter();
        onAttachView();
        initRecyclerVIew();
        presenter.loadCategories();
        WebSettings ws = webView.getSettings();
        ws.setJavaScriptEnabled(true);
    }


    private void initPresenter() {
        presenter = new DashboardCategoryPresenter();
    }

    private void initRecyclerVIew(){
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new HorizCategoryRecyclerViewAdapter(this, categories);
        mAdapter.setOnClick(this);
        recyclerView.setAdapter(mAdapter);
    }
    @Override
    public void onAttachView() {
        presenter.onAttach(this);
    }

    @Override
    public void onDetachView() {
        presenter.onDetach();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void showProgresBar() {
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateRecyclerView(List<Category> categories) {
        this.categories.clear();
        this.categories.addAll(categories);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setWordCloud(List<TopWords> topWords) {
        webView.loadUrl("file:///android_asset/d3.html");
        wordCloud = new ArrayList<>();
        for(TopWords topWord:topWords){
            for(int i = 0; i < topWord.getWeight();i++){
                wordCloud.add(topWord.getName());
                Log.d("ayam",topWord.getName());
            }
        }
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                StringBuffer sb = new StringBuffer();
                sb.append("wordCloud([");
                for (int i = 0; i < wordCloud.size(); i++) {
                    sb.append("'").append(wordCloud.get(i)).append("'");
                    if (i < wordCloud.size() - 1) {
                        sb.append(",");
                    }
                }
                sb.append("])");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    webView.evaluateJavascript(sb.toString(), null);
                } else {
                    webView.loadUrl("javascript:" + sb.toString());
                }
            }
        });
    }

    @Override
    public void setLineChart(final FreqData data) {
        ArrayList<Entry> lineEntries = new ArrayList<Entry>();
        for(int i =0 ; i < data.getLabels().size(); i++){
            lineEntries.add(new Entry(i, data.getValues().get(i)));
        }


        LineDataSet lineDataSet = new LineDataSet(lineEntries, "Jumlah Data Masuk");
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet.setHighlightEnabled(true);
        lineDataSet.setColor(getResources().getColor(R.color.colorAccent));
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(6);
        lineDataSet.setCircleHoleRadius(3);
        lineDataSet.setDrawHighlightIndicators(true);
        lineDataSet.setHighLightColor(Color.RED);
        lineDataSet.setValueTextSize(12);
        LineData lineData = new LineData(lineDataSet);


      //  lineChart.getDescription().setText("Price in last 12 days");
       // lineChart.getDescription().setTextSize(12);
        lineChart.setDrawMarkers(true);
       lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.getXAxis().setLabelRotationAngle(-30f);
        lineChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if (value >= 0) {
                    if (value <= data.getLabels().size() - 1) {
                        return data.getLabels().get((int) value);
                     }
                    return "";
                    }
                    return "";
                }
        });
        lineChart.animateY(1000);
        lineChart.getXAxis().setGranularityEnabled(true);
        lineChart.getXAxis().setGranularity(1.0f);
        lineChart.getXAxis().setLabelCount(lineDataSet.getEntryCount());
        lineChart.setData(lineData);
    }

    @Override
    public void setTitle(String title) {
        txtCategory.setText(title);
    }

    @Override
    public void hideProgresBar() {
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }
    @Override
    public SessionManager getSessionManager(){
        return  sessionManager;
    }

    @Override
    public void onDestroy() {
        onDetachView();
        super.onDestroy();
    }

    @Override
    public void onItemClick(int position) {
        setTitle(categories.get(position).getCategory());
        presenter.loadCategoryFreq(categories.get(position).getSlug());
    }
}
