package application.ViewController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import application.controller.AdminController;
import application.models.Parts;
import application.models.Rendez_vous;
import application.models.Sales;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class Stat_dashboard {
    @FXML
    private Button btn_rendez_vous;

    @FXML
    private Button btn_ventes;

    @FXML
    private BorderPane sales_container;

    @FXML
    private Text stat_1;

    @FXML
    private Text stat_2;

    @FXML
    private Text stat_3;

    @FXML
    private Text title_state_1;

    @FXML
    private Text title_state_2;

    @FXML
    private Text title_state_3;

    @FXML
    private DatePicker date;

    @FXML
    private Button btn_filtr;

    @FXML
    private LineChart<String, Double> line_chart;

    @FXML
    void rendez_vous(ActionEvent event) {

        // LocalDate datelocal = date.getValue();
        // ArrayList<Rendez_vous> list_rdv = AdminController.statRdvList(datelocal);
        title_state_1.setText("Bénéfice net des services");
        title_state_2.setText("Bénéfice net des pieces");
        title_state_3.setText("Bénéfice de Rendez-vous moyen"); // AdminController.statRdvList(date.getValue());
        // int state_1 = 0;
        // int state_2 = 0;
        // double state_3 = 0;
        // for (Rendez_vous rdv : list_rdv) {

        // state_1 = state_1 + rdv.getPrix();
        // for (int i = 0; i < rdv.getParts().size(); i++) {

        // state_2 = state_2 + (rdv.getParts().get(i).getPrice() -
        // rdv.getParts().get(i).getBuyingprice())
        // * rdv.getParts().get(i).getQuntitie();

        // }
        // }

        // state_3 = (state_1 + state_2) / list_rdv.size();

        // stat_1.setText(Integer.toString(state_1));
        // stat_2.setText(Integer.toString(state_2));
        // stat_3.setText(Double.toString(state_3));

        // CategoryAxis xAxis = new CategoryAxis();
        // NumberAxis yAxis = new NumberAxis();

        // // Create the line chart
        // // LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        // // lineChart.setTitle("Revenue by Day");

        // // Create a series for the data
        // XYChart.Series<String, Double> series = new XYChart.Series<>();
        // series.setName("Revenue");

        // // Define the data as a map from dates to revenue
        // Map<String, Double> revenueByDay = new HashMap<>();
        // for (int i = 0; i < 31; i++) {
        // for (int j = 0; j < list_rdv.size(); j++) {
        // LocalDate ld =
        // list_rdv.get(j).getDate_debut().toInstant().atZone(ZoneId.systemDefault())
        // .toLocalDate();
        // if (ld.getDayOfMonth() == i) {
        // Double rv = 0.0;
        // for (Parts part : list_rdv.get(j).getParts()) {
        // rv = rv + (part.getPrice() - part.getBuyingprice()) * part.getQuntitie();
        // }
        // revenueByDay.put(Integer.toString(i), rv);
        // }
        // }

        // }

        // // ... add more data as needed

        // // Add data to the series
        // for (String date : revenueByDay.keySet()) {

        // double revenue = revenueByDay.get(date);
        // series.getData().add(new XYChart.Data<>(date, revenue));
        // }

        // // Add the series to the line chart
        // line_chart.getData().add(series);

    }

    @FXML
    void ventes(ActionEvent event) {

        // LocalDate datelocal = date.getValue();
        // ArrayList<Sales> list_sales = AdminController.statListSales(datelocal);
        title_state_1.setText("bénéfice net des pieces");
        title_state_2.setText("Le nombre total de pièces vendues");
        title_state_3.setText("Bénéfice de vente moyen");
        // int state_1 = 0;
        // int state_2 = 0;
        // double state_3 = 0;

        // for (Sales sale : list_sales) {

        // for (int i = 0; i < sale.getPartList().size(); i++) {
        // state_1 = state_1 + (sale.getPartList().get(i).getPrice() -
        // sale.getPartList().get(i).getBuyingprice())
        // * sale.getPartList().get(i).getQuntitie();
        // state_2 = state_2 + sale.getPartList().get(i).getQuntitie();

        // }
        // }
        // state_3 = state_1 / state_2;
        // stat_1.setText(Integer.toString(state_1));
        // stat_2.setText(Integer.toString(state_2));
        // stat_3.setText(Double.toString(state_3));

        // CategoryAxis xAxis = new CategoryAxis();
        // NumberAxis yAxis = new NumberAxis();

        // // Create the line chart
        // // LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        // // lineChart.setTitle("Revenue by Day");

        // // Create a series for the data
        // XYChart.Series<String, Double> series = new XYChart.Series<>();
        // series.setName("Revenue");

        // // Define the data as a map from dates to revenue
        // Map<String, Double> revenueByDay = new HashMap<>();
        // for (int i = 0; i < 31; i++) {
        // for (int j = 0; j < list_sales.size(); j++) {
        // LocalDate ld =
        // list_sales.get(j).getDate_de_vente().toInstant().atZone(ZoneId.systemDefault())
        // .toLocalDate();
        // if (ld.getDayOfMonth() == i) {
        // Double rv = 0.0;
        // for (Parts part : list_sales.get(j).getPartList()) {
        // rv = rv + (part.getPrice() - part.getBuyingprice()) * part.getQuntitie();
        // }
        // revenueByDay.put(Integer.toString(i), rv);
        // }
        // }

        // }

        // // ... add more data as needed

        // // Add data to the series
        // for (String date : revenueByDay.keySet()) {

        // double revenue = revenueByDay.get(date);
        // series.getData().add(new XYChart.Data<>(date, revenue));
        // }

        // // Add the series to the line chart
        // line_chart.getData().add(series);

    }

    @FXML
    void filtre(ActionEvent event) {

        System.out.println("here berfore if");
        if (title_state_1.getText().equals("bénéfice net des pieces")) {
            System.out.println("here after if");

            LocalDate datelocal = date.getValue();
            ArrayList<Sales> list_sales = AdminController.statListSales(datelocal);
            int state_1 = 0;
            int state_2 = 0;
            double state_3 = 0;

            for (Sales sale : list_sales) {

                for (int i = 0; i < sale.getPartList().size(); i++) {
                    state_1 = state_1
                            + (sale.getPartList().get(i).getPrice() - sale.getPartList().get(i).getBuyingprice())
                                    * sale.getPartList().get(i).getQuntitie();
                    state_2 = state_2 + sale.getPartList().get(i).getQuntitie();

                }
            }
            state_3 = state_1 / state_2;
            stat_1.setText(Integer.toString(state_1));
            stat_2.setText(Integer.toString(state_2));
            stat_3.setText(Double.toString(state_3));

            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();

            // Create the line chart
            // LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
            // lineChart.setTitle("Revenue by Day");

            // Create a series for the data
            XYChart.Series<String, Double> series = new XYChart.Series<>();
            series.setName("Revenue");

            // Define the data as a map from dates to revenue
            Map<String, Double> revenueByDay = new HashMap<>();

            for (int j = 0; j < list_sales.size(); j++) {
                LocalDate ld = list_sales.get(j).getDate_de_vente().toInstant().atZone(ZoneId.systemDefault())
                        .toLocalDate();

                Double rv = 0.0;
                for (Parts part : list_sales.get(j).getPartList()) {
                    rv = rv + (part.getPrice() - part.getBuyingprice()) * part.getQuntitie();

                    revenueByDay.put(Integer.toString(ld.getDayOfMonth()), rv);

                }

            }

            // ... add more data as needed

            // Add data to the series
            for (String date : revenueByDay.keySet()) {

                double revenue = revenueByDay.get(date);
                series.getData().add(new XYChart.Data<>(date, revenue));
            }

            // Add the series to the line chart
            line_chart.getData().add(series);

        } else if (title_state_1.getText().equals("Bénéfice net des services")) {
            LocalDate datelocal = date.getValue();
            ArrayList<Rendez_vous> list_rdv = AdminController.statRdvList(datelocal);

            int state_1 = 0;
            int state_2 = 0;
            double state_3 = 0;
            for (Rendez_vous rdv : list_rdv) {

                state_1 = state_1 + rdv.getPrix();
                for (int i = 0; i < rdv.getParts().size(); i++) {

                    state_2 = state_2 + (rdv.getParts().get(i).getPrice() - rdv.getParts().get(i).getBuyingprice())
                            * rdv.getParts().get(i).getQuntitie();

                }
            }

            state_3 = (state_1 + state_2) / list_rdv.size();

            stat_1.setText(Integer.toString(state_1));
            stat_2.setText(Integer.toString(state_2));
            stat_3.setText(Double.toString(state_3));

            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();

            // Create the line chart
            // LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
            // lineChart.setTitle("Revenue by Day");

            // Create a series for the data
            XYChart.Series<String, Double> series = new XYChart.Series<>();
            series.setName("Revenue");

            Map<String, Double> revenueByDay = new HashMap<>();
            for (int j = 0; j < list_rdv.size(); j++) {
                LocalDate ld = list_rdv.get(j).getDate_debut().toInstant().atZone(ZoneId.systemDefault())
                        .toLocalDate();
                Double rv = 0.0;
                for (Parts part : list_rdv.get(j).getParts()) {
                    rv = rv + (part.getPrice() - part.getBuyingprice()) * part.getQuntitie();
                }
                revenueByDay.put(Integer.toString(ld.getDayOfMonth()), rv);

            }

            // ... add more data as needed

            // Add data to the series
            for (String date : revenueByDay.keySet()) {

                double revenue = revenueByDay.get(date);
                series.getData().add(new XYChart.Data<>(date, revenue));
            }

            // Add the series to the line chart
            line_chart.getData().add(series);
        }

    }

}
