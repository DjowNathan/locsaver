package br.usjt.locsaver.model;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.GeoPoint;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Localizacao implements Serializable {

        private String description;

        private Timestamp createdAt;

        private GeoPoint coordinates;

        private String id;

        public Map<String, Object> toMap(){
                Map<String, Object> map = new HashMap<>();
                map.put("description", this.description);
                map.put("createdAt", this.createdAt);
                map.put("coordinates", this.coordinates);

                if(!this.id.isEmpty()){
                        map.put("id", this.id);
                }

                return map;
        }

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public Timestamp getCreatedAt() {
                return createdAt;
        }

        public void setCreatedAt(Timestamp createdAt) {
                this.createdAt = createdAt;
        }

        public GeoPoint getCoordinates() {
                return coordinates;
        }

        public void setCoordinates(double latitude, double longitude) {
                this.coordinates = new GeoPoint(latitude, longitude);
        }

        @Override
        public String toString() {
                return "Localizacao {" +
                        "\ndescription='" + description + '\'' +
                        ", \ncreatedAt=" + createdAt +
                        ", \ncoordinates=" + coordinates +
                        "}\n";
        }
}