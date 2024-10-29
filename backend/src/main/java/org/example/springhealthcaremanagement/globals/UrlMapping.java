package org.example.springhealthcaremanagement.globals;

public class UrlMapping {
  public static final String API = "/api";
  public static final String ADDRESS = API + "/address";
  public static final String APPOINTMENT = API + "/appointment";
  public static final String DOCTOR = API + "/doctor";
  public static final String USER = API + "/user";
  public static final String PATIENT = API + "/patient";
  public static final String GENDER = API + "/gender";
  public static final String MEDICATION = API + "/medication";
  public static final String MEDICAL_RECORD = API + "/medical-record";
  public static final String SPECIALIZATION = API + "/specialization";
  public static final String ADDRESS_ID_PATH ="/findById/{id}";
  public static final String ADDRESS_CITY_PATH ="/findByCity/{city}";

  public static final String AUTH = API + "/auth";

  public static final String SIGN_IN = "/sign-in";
  public static final String SIGN_UP = "/sign-up";
  public static final String SIGN_UP_PATIENT = "/sign-up-patient";
  public static final String SIGN_UP_DOCTOR = "/sign-up-doctor";
  public static final String FIND_APPOINTMENTS_BY_DOCTOR_ID = "/find-appointments-by-doctorId/{doctorId}";
  public static final String FIND_APPOINTMENTS_BY_PATIENT_ID = "/find-upcoming-appointments-for-patient/{username}";
  public static final String FIND_PAST_APPOINTMENTS_FOR_PATIENT = "/find-past-appointments-for-patient/{username}";
  public static final String CANCEL_APPOINTMENT = "/cancel-appointment/{appointmentId}";
    public static final String REFUSE_APPOINTMENT = "/refuse-appointment/{appointmentId}";
    public static final String CONFIRM_APPOINTMENT = "/confirm-appointment/{appointmentId}";
    public static final String FIND_UPCOMING_APPOINTMENTS_FOR_DOCTOR = "/find-upcoming-appointments-for-doctor/{username}";
    public static final String FIND_PAST_APPOINTMENTS_FOR_DOCTOR = "/find-past-appointments-for-doctor/{username}";
    public static final String FIND_REQUESTED_APPOINTMENTS_FOR_DOCTOR = "/find-requested-appointments-for-doctor/{username}";
    public static final String FIND_DOCTOR_BY_USERNAME = "/{username}";
    public static final String FIND_DOCTORS_WITH_SPECIALIZATION = "/find-doctors-with-specialization/{specialization}";
    public static final String FIND_MEDICAL_RECORDS_BY_DOCTOR_USERNAME = "/doctor/{doctorUsername}";
    public static final String FIND_MEDICAL_RECORDS_BY_PATIENT_USERNAME = "/patient/{patientUsername}";
    public static final String FIND_PATIENT_BY_USERNAME = "/{username}";
    public static final String FIND_USER_BY_USERNAME = "/{username}";
}