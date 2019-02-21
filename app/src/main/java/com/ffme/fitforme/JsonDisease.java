package com.ffme.fitforme;

import java.util.List;

public class JsonDisease {

    List<DiseaseBean> data;

    public List<DiseaseBean> getData() {
        return data;
    }

    public void setData(List<DiseaseBean> data) {
        this.data = data;
    }

    public static class DiseaseBean {

        private Integer Id;
        private String Name;

        public Integer getId() {
            return Id;
        }

        public void setId(Integer id) {
            this.Id = Id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            this.Name = Name;
        }
    }

}
