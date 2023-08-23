package com.jjuarez.gila.entity;


import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name = "topics")
public class Topic {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "name")
        private String name;

        public Topic() {
        }

        public Topic(Long id, String name) {
                this.id = id;
                this.name = name;
        }

        public Long getId() {
                return id;
        }

        public String getName() {
                return name;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public void setName(String name) {
                this.name = name;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                Topic topic = (Topic) o;

                return Objects.equals(name, topic.name);
        }

        @Override
        public int hashCode() {
                return name != null ? name.hashCode() : 0;
        }
}
