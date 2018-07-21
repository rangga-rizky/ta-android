package com.example.ranggarizky.tugas_akhir.mainpage;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ranggarizky.tugas_akhir.R;
import com.example.ranggarizky.tugas_akhir.dashboardcategorypage.DashBoardCategoryActivity;
import com.example.ranggarizky.tugas_akhir.dashboardcategorypage.DashboardCategoryView;
import com.example.ranggarizky.tugas_akhir.databarupage.NewDataActivity;
import com.example.ranggarizky.tugas_akhir.loginpage.LoginPresenter;
import com.example.ranggarizky.tugas_akhir.model.FreqData;
import com.example.ranggarizky.tugas_akhir.model.MostCategorized;
import com.example.ranggarizky.tugas_akhir.model.ResponseDashboard;
import com.example.ranggarizky.tugas_akhir.utils.SessionManager;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashBoardFragment extends Fragment implements DashboardView {
    DashBoardPresenter presenter;
    @BindView(R.id.txtjumlahKategori)
    TextView txtjumlahKategori;
    @BindView(R.id.pieChart)
    PieChart pieChart;
    @BindView(R.id.txtKategoriTerbanyak)
    TextView txtKategoriTerbanyak;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.txtUsername)
    TextView txtUsername;
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.txtjumlahData)
    TextView txtjumlahData;
    @BindView(R.id.txtLastLogin)
    TextView txtLastLogin;
    @BindView(R.id.mainLayout)
    ConstraintLayout mainLayout;
    @BindView(R.id.lineChart)
    LineChart lineChart;
    SessionManager sessionManager;

    public DashBoardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dash_board, container, false);
        ButterKnife.bind(this,view);
        sessionManager = new SessionManager(getContext());
        initPresenter();
        onAttachView();
         presenter.loadDashboard();
        return view;
    }


    private void initPresenter() {
        presenter = new DashBoardPresenter();
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
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void setMeta(ResponseDashboard dashboardObject) {
        txtjumlahData.setText(dashboardObject.getnData().toString()+" data");
        txtjumlahKategori.setText(dashboardObject.getnCategory().toString()+" kategori");
        txtUsername.setText((dashboardObject.getUserName()));
        txtLastLogin.setText(sessionManager.getLastLogin());
        txtKategoriTerbanyak.setText(dashboardObject.getMostCategorized().get(0).getPredicted());
    }

    @Override
    public void setpieChart(List<MostCategorized> data) {
       ArrayList<PieEntry> entries = new ArrayList<>();
        for(int i = 0; i < data.size(); i++){
            entries.add(new PieEntry(data.get(i).getY(),data.get(i).getName()));
        }

        pieChart.setRotationEnabled(false);

        //create the data set
        PieDataSet pieDataSet = new PieDataSet(entries, "");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);
        colors.add(getResources().getColor(R.color.colorAccent));
        colors.add(getResources().getColor(R.color.colorPrimary));
        colors.add(getResources().getColor(R.color.clock_grey));
        colors.add(getResources().getColor(R.color.hijau_daun));

        pieDataSet.setColors(colors);
        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setWordWrapEnabled(true);
        pieChart.setDrawSliceText(false);

        pieDataSet.setValueFormatter(new PercentFormatter());
        PieData pieData = new PieData(pieDataSet);
        pieChart.setUsePercentValues(true);
        pieChart.setData(pieData);
        pieChart.highlightValues(null);
        pieChart.invalidate();
    }

    @OnClick(R.id.btnToNewPage)
    public void toNewPage(View view){
        toNewDataPage();
    }

    @OnClick(R.id.btnToDashboardCategory)
    public void toDashboardCategory(View view){
        toDashboardCategory();
    }

    @Override
    public void toNewDataPage() {
        Intent intent = new Intent(getActivity(), NewDataActivity.class);
        startActivity(intent);
    }

    @Override
    public void toDashboardCategory() {
        Intent intent = new Intent(getActivity(), DashBoardCategoryActivity.class);
        startActivity(intent);
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
    public void showProgresBar() {
        mainLayout.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgresBar() {
        progressBar.setVisibility(View.GONE);
        mainLayout.setVisibility(View.VISIBLE);
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
}
