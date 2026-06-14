package com.universal.payroll.config;

public enum FxmlView {

    LOGIN {
        @Override
        public String getFxmlPath(){
            return "view/login.fxml";
        }
    },

    HOME {
        @Override
        public String getFxmlPath(){
            return "view/home.fxml";
        }
    };

    public abstract String getFxmlPath();
}
