package br.usjt.locsaver.model;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.GeoPoint;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Localizacao implements Serializable {

        @DocumentId
        private String id;

        private String description;

        private Timestamp createdAt;

        private GeoPoint coordinates;

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
}