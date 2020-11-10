package br.usjt.locsaver;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDate;

public class Localizacao implements Serializable {
        @SerializedName("_id")
        @Expose
        private String id;

        @Expose
        private String descricao;

        @Expose
        private LocalDate criadoEm;

        @Expose
        private Double latitude;

        @Expose
        private Double longitude;

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getDescricao() {
                return descricao;
        }

        public void setDescricao(String descricao) {
                this.descricao = descricao;
        }

        public LocalDate getCriadoEm() {
                return criadoEm;
        }

        public void setCriadoEm(LocalDate criadoEm) {
                this.criadoEm = criadoEm;
        }

        public Double getLatitude() {
                return latitude;
        }

        public void setLatitude(Double latitude) {
                this.latitude = latitude;
        }

        public Double getLongitude() {
                return longitude;
        }

        public void setLongitude(Double longitude) {
                this.longitude = longitude;
        }
}
