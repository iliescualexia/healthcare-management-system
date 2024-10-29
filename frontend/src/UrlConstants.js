class UrlConstants {
    static BASE_URL = 'http://localhost:8080/api';
    static SPECIALIZATION_URL = `${UrlConstants.BASE_URL}/specialization`;
    static APPOINTMENTS_URL = `${UrlConstants.BASE_URL}/appointment`;
    static DOCTOR_URL = `${UrlConstants.BASE_URL}/doctor`;
    static PATIENT_URL = `${UrlConstants.BASE_URL}/patient`;
    static USER_URL = `${UrlConstants.BASE_URL}/user`;
    static AUTH_URL = `${UrlConstants.BASE_URL}/auth`;
    static SIGN_UP_URL = `${UrlConstants.AUTH_URL}/sign-up`;
    static SIGN_UP_PATIENT_URL = `${UrlConstants.AUTH_URL}/sign-up-patient`;
    static SIGN_UP_DOCTOR_URL = `${UrlConstants.AUTH_URL}/sign-up-doctor`;
    static SIGN_IN_URL = `${UrlConstants.AUTH_URL}/sign-in`;
    static MEDICAL_RECORDS_URL =  `${UrlConstants.BASE_URL}/medical-record`;
  }
  
  export default UrlConstants;