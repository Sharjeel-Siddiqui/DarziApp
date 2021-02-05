package com.example.dulha_jee.pojo;

import java.util.ArrayList;

public class SearchResponseBody {


    private ArrayList<user> data;

    public ArrayList<user> getData() {
        return data;
    }

    public void setData(ArrayList<user> data) {
        this.data = data;
    }

    public class user{
        private String order_date;
        private String mobile_number;
        private String customer_name;
        private String order_number;
        private String karigar;
        private String html;
        private String order_status;

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public String getOrder_date() {
            return order_date;
        }

        public void setOrder_date(String order_date) {
            this.order_date = order_date;
        }

        public String getMobile_number() {
            return mobile_number;
        }

        public void setMobile_number(String mobile_number) {
            this.mobile_number = mobile_number;
        }

        public String getCustomer_name() {
            return customer_name;
        }

        public void setCustomer_name(String customer_name) {
            this.customer_name = customer_name;
        }

        public String getOrder_number() {
            return order_number;
        }

        public void setOrder_number(String order_number) {
            this.order_number = order_number;
        }

        public String getKarigar() {
            return karigar;
        }

        public void setKarigar(String karigar) {
            this.karigar = karigar;
        }

        public String getHtml() {
            return html;
        }

        public void setHtml(String html) {
            this.html = html;
        }
    }
}
