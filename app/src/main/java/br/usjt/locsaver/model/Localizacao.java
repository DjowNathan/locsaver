package br.usjt.locsaver.model;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.GeoPoint;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Localizacao implements Serializable {

        private String id;

        private String description;

        private Timestamp createdAt;

        private GeoPoint coordinates;

        public Map<String, Object> toMap(){
                Map<String, Object> map = new HashMap<>();

                if(!this.id.isEmpty() && this.id != null)
                        map.put("id", this.id);

                map.put("description", this.description);
                map.put("createdAt", this.createdAt);
                map.put("coordinates", this.coordinates);

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
                return "Localizacao{" +
                        "id='" + id + '\'' +
                        ", description='" + description + '\'' +
                        ", createdAt=" + createdAt +
                        ", coordinates=" + coordinates +
                        '}';
        }
}