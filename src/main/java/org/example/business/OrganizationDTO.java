package org.example.business;

public class OrganizationDTO {
    private final String email;
    private final String name;
    private final String representativeFullName;
    private final String colony;
    private final String street;
    private final String state;
    private final String createdAt;

    public OrganizationDTO(Builder builder) {
        this.email = builder.email;
        this.name = builder.name;
        this.representativeFullName = builder.representativeFullName;
        this.colony = builder.colony;
        this.street = builder.street;
        this.state = builder.state;
        this.createdAt = builder.createdAt;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getRepresentativeFullName() {
        return representativeFullName;
    }

    public String getColony() {
        return colony;
    }

    public String getStreet() {
        return street;
    }

    public String getState() {
        return state;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public static class Builder {
        protected String email;
        protected String name;
        protected String representativeFullName;
        protected String colony;
        protected String street;
        protected String state;
        protected String createdAt;

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setRepresentativeFullName(String representativeFullName){
            this.representativeFullName = representativeFullName;
            return this;
        }

        public Builder setColony(String colony){
            this.colony = colony;
            return this;
        }

        public Builder setStreet(String street){
            this.street = street;
            return this;
        }

        public Builder setState(String state){
            this.state = state;
            return this;
        }

        public Builder setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public OrganizationDTO build() {
            return new OrganizationDTO(this);
        }
    }
}

