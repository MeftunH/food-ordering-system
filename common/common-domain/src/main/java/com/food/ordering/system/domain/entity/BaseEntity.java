package com.food.ordering.system.domain.entity;

import java.util.Objects;

public abstract class BaseEntity<ID> {
    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    //each entity must have an unique id
    @Override
    public int hashCode() {
      return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
       if(this == obj) {
           return true;
       }
       if(obj == null || getClass() != obj.getClass()) {
           return false;
       }
       BaseEntity<?> other = (BaseEntity<?>) obj;
       return id.equals(other.id);
    }
}
