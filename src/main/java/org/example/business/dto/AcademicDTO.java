package org.example.business.dto;

import org.example.business.Validator;

public class AcademicDTO extends Person {
  private final String id;
  private final String role;

  public AcademicDTO(AcademicBuilder builder) {
    super(builder);
    this.id = builder.id;
    this.role = builder.role;
  }

  public String getID() {
    return id;
  }

  public String getRole() {
    return role;
  }

  @Override
  public boolean equals(Object instance) {
    if (this == instance) return true;
    if (instance == null || getClass() != instance.getClass()) return false;

    AcademicDTO that = (AcademicDTO) instance;

    return id.equals(that.id) && role.equals(that.role) && super.equals(that);
  }

  public static class AcademicBuilder extends PersonBuilder<AcademicBuilder> {
    private String id;
    private String role;

    public AcademicBuilder setID(String id) throws IllegalArgumentException {
      this.id = Validator.getValidWorkerID(id);
      return this;
    }

    public AcademicBuilder setRole(String role) throws IllegalArgumentException {
      this.role = Validator.getValidAcademicRole(role);
      return this;
    }

    @Override
    public AcademicDTO build() {
      return new AcademicDTO(this);
    }
  }
}
