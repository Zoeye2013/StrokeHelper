package fi.etla.strokehelper.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class DataBaseManage extends SQLiteOpenHelper {

	/** Database Info */
	public static final String DATABASE_NAME = "song.db";
	public static final int DATABASE_VERSION = 1;

	/** Tables Info */
	public static final String TABLE_DOCTOR_USERS = "doctor_user";
	public static final String TABLE_TIMESTAMPS = "timestamps";
	public static final String TABLE_PATIENTS = "patients";
	// public static final String TABLE_PRE_NOTIFICATION = "pre_notification";
	public static final String TABLE_ANAMNESIS = "anamnesis";
	public static final String TABLE_PRESENT_CONDITIONS = "pre_condition";
	public static final String TABLE_SYMPTOMS = "symptoms";
	public static final String TABLE_LAB = "lab";
	public static final String TABLE_NIHSS = "nihss";
	public static final String TABLE_THROMBOLYSIS = "thrombolysis";
	public static final String TABLE_CONTRAINDICATION = "contraindication";
	public static final String TABLE_INTERRUPTION = "interruption";

	/** Common columns */
	public static final String ROW_ID = "_rowid_";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_LOG_TIME = "log_time";
	public static final String COLUMN_DOCTOR_ID = "doctor_id";
	public static final String COLUMN_PATIENT_ID = "patient_id";
	public static final String COLUMN_IS_HISTORY_RECORD = "is_history";

	/** Doctor User Table columns */
	public static final String COLUMN_DOCTOR_USERNAME = "doctor_username";
	public static final String COLUMN_DOCTOR_PWD = "doctor_pwd";

	/** TimeStamps Table columns */
	public static final String COLUMN_PREDOOR_TIME = "predoor_time";
	public static final String COLUMN_POSTDOOR_TIME = "postdoor_time";
	public static final String COLUMN_ONSET_LOCK = "onset_lock";
	public static final String COLUMN_ONSET_TIME = "onset_time";
	public static final String COLUMN_ONSET_HOUR = "onset_h";
	public static final String COLUMN_ONSET_MIN = "onset_min";
	public static final String COLUMN_ONSET_QUALITY_ID = "onset_quality_id";
	public static final String COLUMN_ONSET_QUALITY = "onset_quality";
	public static final String COLUMN_ESTIMATE_LOCK = "estimate_lock";
	public static final String COLUMN_ESTIMATE_ARRIVAL_TIME = "estimate_arrival_time";
	public static final String COLUMN_ESTIMATE_ARRIVAL_HOUR = "estiArri_h";
	public static final String COLUMN_ESTIMATE_ARRIVAL_MIN = "estiArri_min";
	public static final String COLUMN_DOOR_TIME = "door_time";
	public static final String COLUMN_CT_BEGIN_TIME = "ct_begin_time";
	public static final String COLUMN_CT_STATEMENT_TIME = "ct_statement_time";
	public static final String COLUMN_THROMBOLYSIS_DECISION_TIME = "thrombolysis_decision_time";
	public static final String COLUMN_BOLUS_TIME = "bolus_time";
	public static final String COLUMN_INFUSION_BEGIN_TIME = "infusion_begin_time";
	public static final String COLUMN_INFUSION_END_TIME = "infusion_end_time";
	public static final String COLUMN_FOLLOWUP_END_TIME = "followup_end_time";
	public static final String COLUMN_INTERRUPT_TREAT_TIME = "interrupt_treat_time";

	/** Patient Table columns */
	public static final String COLUMN_PATIENT_TYPE = "patient_type";
	public static final String COLUMN_ON_HOSPITAL_DATE = "enter_date";
	public static final String COLUMN_BIRTHDAY_LOCK = "birthday_lock";
	public static final String COLUMN_DATE_OF_BIRTH = "date_of_birth";
	public static final String COLUMN_AGE = "age";
	public static final String COLUMN_WEIGHT = "weight";
	public static final String COLUMN_THROMBOLYSIS_CANDIDATE = "thrombolysis_candidate";
	public static final String COLUMN_THROMBOLYSIS_CANDIDATE_ID = "thrombolysis_candidate_id";
	public static final String COLUMN_WHY = "why";
	public static final String COLUMN_BIRTH_DAY = "birth_day";
	public static final String COLUMN_BIRTH_MONTH = "birth_month";
	public static final String COLUMN_BIRTH_YEAR = "birth_year";
	public static final String COLUMN_PATIENT_TYPE_ID = "patient_type_id";

	/** Symptoms Table columns */
	public static final String COLUMN_SYMPTOM_SIDE = "symptom_side";
	public static final String COLUMN_LIMB_SYMPTOMS = "limb_symptoms";
	public static final String COLUMN_FACIAL_PALSY_SYMPTOMS = "facial_palsy_symptoms";
	public static final String COLUMN_SPEECH_DISORDER = "speech_disorder";
	public static final String COLUMN_CONVULSIONS = "convulsions";
	public static final String COLUMN_BASILARIS_SYMPTOMS = "basilaris_symptoms";
	public static final String COLUMN_CARE_FOR_SELF = "cares_for_self";
	public static final String COLUMN_TERMINAL_DISEASE = "terminal_disease";
	public static final String COLUMN_ANTICOAGULANT = "anticoagulant";
	public static final String COLUMN_QUICK_RECOVER_STROKE_SYMPTOMS = "quick_recover_symptoms";
	public static final String COLUMN_SYMPTOMS_SUGGEST_SAV = "symptom_suggest_sav";
	public static final String COLUMN_MILD_STROKE_SYMPTOMS = "mild_symptoms";

	public static final String COLUMN_SYMPTOM_SIDE_ID = "symptom_side_id";
	public static final String COLUMN_LIMB_SYMPTOMS_ID = "limb_symptoms_id";
	public static final String COLUMN_FACIAL_PALSY_SYMPTOMS_ID = "facial_palsy_symptoms_id";
	public static final String COLUMN_SPEECH_DISORDER_ID = "speech_disorder_id";
	public static final String COLUMN_CONVULSIONS_ID = "convulsions_id";
	public static final String COLUMN_BASILARIS_SYMPTOMS_ID = "basilaris_symptoms_id";
	public static final String COLUMN_CARE_FOR_SELF_ID = "cares_for_self_id";
	public static final String COLUMN_TERMINAL_DISEASE_ID = "terminal_disease_id";
	public static final String COLUMN_ANTICOAGULANT_ID = "anticoagulant_id";
	public static final String COLUMN_QUICK_RECOVER_STROKE_SYMPTOMS_ID = "quick_recover_symptoms_id";
	public static final String COLUMN_SYMPTOMS_SUGGEST_SAV_ID = "symptom_suggest_sav_id";
	public static final String COLUMN_MILD_STROKE_SYMPTOMS_ID = "mild_symptoms_id";

	/** NIHSS Table columns */
	public static final String COLUMN_NIHSS_TYPE = "nihss_type";
	public static final String COLUMN_1A_CONSCIOUSNESS_LEVEL = "consciousness_level";
	public static final String COLUMN_1A_CONSCIOUSNESS_LEVEL_ID = "consciousness_level_id";
	public static final String COLUMN_1A_CONSCIOUSNESS_LEVEL_POINT = "consciousness_level_point";

	public static final String COLUMN_1B_LOC_QUESTIONS = "loc_questions";
	public static final String COLUMN_1B_LOC_QUESTIONS_ID = "loc_questions_id";
	public static final String COLUMN_1B_LOC_QUESTIONS_POINT = "loc_questions_point";

	public static final String COLUMN_1C_LOC_COMMANDS = "loc_commands";
	public static final String COLUMN_1C_LOC_COMMANDS_ID = "loc_commands_id";
	public static final String COLUMN_1C_LOC_COMMANDS_POINT = "loc_commands_point";

	public static final String COLUMN_2_BEST_GAZE = "best_gaze";
	public static final String COLUMN_2_BEST_GAZE_ID = "best_gaze_id";
	public static final String COLUMN_2_BEST_GAZE_POINT = "best_gaze_point";

	public static final String COLUMN_3_VISUAL = "visual";
	public static final String COLUMN_3_VISUAL_ID = "visual_id";
	public static final String COLUMN_3_VISUAL_POINT = "visual_point";

	public static final String COLUMN_4_FACIAL_PALSY = "facial_palsy";
	public static final String COLUMN_4_FACIAL_PALSY_ID = "facial_palsy_id";
	public static final String COLUMN_4_FACIAL_PALSY_POINT = "facial_palsy_point";

	public static final String COLUMN_5A_MOTOR_ARM_RIGHT = "motor_arm_right";
	public static final String COLUMN_5A_MOTOR_ARM_RIGHT_ID = "motor_arm_right_id";
	public static final String COLUMN_5A_MOTOR_ARM_RIGHT_POINT = "motor_arm_right_point";

	public static final String COLUMN_5B_MOTOR_ARM_LEFT = "motor_arm_left";
	public static final String COLUMN_5B_MOTOR_ARM_LEFT_ID = "motor_arm_left_id";
	public static final String COLUMN_5B_MOTOR_ARM_LEFT_POINT = "motor_arm_left_point";

	public static final String COLUMN_6A_MOTOR_LEG_RIGHT = "motor_leg_right";
	public static final String COLUMN_6A_MOTOR_LEG_RIGHT_ID = "motor_leg_right_id";
	public static final String COLUMN_6A_MOTOR_LEG_RIGHT_POINT = "motor_leg_right_point";

	public static final String COLUMN_6B_MOTOR_LEG_LEFT = "motor_leg_left";
	public static final String COLUMN_6B_MOTOR_LEG_LEFT_ID = "motor_leg_left_id";
	public static final String COLUMN_6B_MOTOR_LEG_LEFT_POINT = "motor_leg_left_point";

	public static final String COLUMN_7_LIMB_ATAXIA = "limb_ataxia";
	public static final String COLUMN_7_LIMB_ATAXIA_ID = "limb_ataxia_id";
	public static final String COLUMN_7_LIMB_ATAXIA_POINT = "limb_ataxia_point";

	public static final String COLUMN_8_SENSORY = "sensory";
	public static final String COLUMN_8_SENSORY_ID = "sensory_id";
	public static final String COLUMN_8_SENSORY_POINT = "sensory_point";

	public static final String COLUMN_9_BEST_LANGUAGE = "best_language";
	public static final String COLUMN_9_BEST_LANGUAGE_ID = "best_language_id";
	public static final String COLUMN_9_BEST_LANGUAGE_POINT = "best_language_point";

	public static final String COLUMN_10_DYSARTHRIA = "dysarthria";
	public static final String COLUMN_10_DYSARTHRIA_ID = "dysarthria_id";
	public static final String COLUMN_10_DYSARTHRIA_POINT = "dysarthria_point";

	public static final String COLUMN_11_EXTINCTION_INATTENTION = "extinction_and_inattention";
	public static final String COLUMN_11_EXTINCTION_INATTENTION_ID = "extinction_and_inattention_id";
	public static final String COLUMN_11_EXTINCTION_INATTENTION_POINT = "extinction_and_inattention_point";

	public static final String COLUMN_TOTAL = "nihss_total";

	/** Lab */
	public static final String COLUMN_RR_SYSTOLIC = "rr_systolic";
	public static final String COLUMN_RR_DIASTOLIC = "rr_diastolic";
	public static final String COLUMN_RR_TREAT_DECISION = "rr_treat";
	public static final String COLUMN_RR_AFTER_SYSTOLIC = "rr_after_treat_systolic";
	public static final String COLUMN_RR_AFTER_DIASTOLIC = "rr_after_treat_diastolic";
	public static final String COLUMN_MODIFIED_RANKIN_SCALE = "modified_rankin_scale";
	public static final String COLUMN_MODIFIED_RANKIN_SCALE_ID = "modified_rankin_scale_id";
	/** Save string resource Id */
	public static final String COLUMN_ADMIS_CT_NO_VISILE_SIGN = "admi_ct_no_visible_sign";
	public static final String COLUMN_ADMIS_CT_HYPER_SIGN = "admi_ct_hyper_sign";
	public static final String COLUMN_ADMIS_CT_EARLY_INFARCT_SIGN = "admi_ct_early_infarct_sign";
	public static final String COLUMN_ADMIS_CT_BLEEDING = "admi_ct_bleeding";
	public static final String COLUMN_ADMIS_CT_ISCHEMIA_LESS = "admi_ct_ischemia_less";
	public static final String COLUMN_ADMIS_CT_ISCHEMIA_MORE = "admi_ct_ischemia_more";
	public static final String COLUMN_ADMIS_CT_TUMOR = "admi_ct_tumor";
	public static final String COLUMN_ADMIS_CT_ABSCESS = "admi_ct_abscess";
	public static final String COLUMN_ADMIS_CT_OTHER = "admi_ct_other";

	public static final String COLUMN_PERFUSION_CT = "perfusion_ct";
	public static final String COLUMN_PERFUSION_CT_ID = "perfusion_ct_id";
	public static final String COLUMN_PERFUSION_CT_RESULTS = "perfusion_ct_results";
	public static final String COLUMN_PERFUSION_CT_RESULTS_ID = "perfusion_ct_results_id";
	public static final String COLUMN_PERFUSION_CT_NOT_DONE_WHY = "perfusion_ct_why_not";

	public static final String COLUMN_CT_ANGIOGRAPHY = "ct_angiography";
	public static final String COLUMN_CT_ANGIOGRAPHY_ID = "ct_angiography_id";
	public static final String COLUMN_CT_ANGIOGRAPHY_RESULTS = "ct_angiography_results";
	public static final String COLUMN_CT_ANGIOGRAPHY_RESULTS_ID = "ct_angiography_results_id";
	public static final String COLUMN_CT_ANGIOGRAPHY_NOT_DONE_WHY = "ct_angiography_why_not";

	public static final String COLUMN_IS_ADMIS_CT_RECOMMEND = "admi_ct_recommend";
	public static final String COLUMN_IS_PERFUSION_CT_RECOMMEND = "perfusion_ct_recommend";
	public static final String COLUMN_IS_CT_ANGIOGRAPHY_RECOMMEND = "ct_angiography_recommend";

	public static final String COLUMN_QUICK_INR = "quick_inr";
	public static final String COLUMN_INR = "inr";
	public static final String COLUMN_APTT = "aptt";
	public static final String COLUMN_P_TROMBAI = "p_trombai";
	public static final String COLUMN_B_TROM = "b_trom";
	public static final String COLUMN_B_GLUC = "b_gluc";
	public static final String COLUMN_B_GLUC_TREAT_DECISION = "b_gluc_treat";
	public static final String COLUMN_B_GLUC_AFTER_BALANCING = "b_gluc_after_blood_sugar_balancing";

	/** Present critical condition */
	public static final String COLUMN_ACTIVE_BLEED = "active_bleeding";
	public static final String COLUMN_ACTIVE_BLEED_ID = "active_bleeding_id";

	public static final String COLUMN_ANTICOAGULANT_TREAT = "anticoagulant_treat";
	public static final String COLUMN_ANTICOAGULANT_TREAT_ID = "anticoagulant_treat_id";
	public static final String COLUMN_ANTICOAGULANT_HEPARIN = "heparin_LMWH";
	public static final String COLUMN_ANTICOAGULANT_HEPARIN_ID = "heparin_LMWH_id";
	public static final String COLUMN_ANTICOAGULANT_DABIGATRAN = "dabigatran";
	public static final String COLUMN_ANTICOAGULANT_DABIGATRAN_ID = "dabigatran_id";
	public static final String COLUMN_ANTICOAGULANT_APIKSABAN = "apiksaban_rivaroksaban";
	public static final String COLUMN_ANTICOAGULANT_APIKSABAN_ID = "apiksaban_rivaroksaban_id";

	public static final String COLUMN_CARDIOVASCULAR_INFECTIONS = "cardiovascular_infections";
	public static final String COLUMN_CARDIOVASCULAR_INFECTIONS_ID = "cardiovascular_infections_id";
	public static final String COLUMN_CARDIOVASCULAR_ENDOCARDITIS = "cardiovascular_endocarditis";
	public static final String COLUMN_CARDIOVASCULAR_PERICARDITIS = "cardiovascular_pericarditis";
	public static final String COLUMN_CARDIOVASCULAR_EMBOLUS = "cardiovascular_embolus";
	public static final String COLUMN_CARDIOVASCULAR_OTHER_CRITICAL = "cardiovascular_other_critical";
	public static final String COLUMN_CARDIOVASCULAR_OTHER_NOT_CRITICAL = "cardiovascular_other_not_critical";

	public static final String COLUMN_OBSTETRIC = "obstetric";
	public static final String COLUMN_OBSTETRIC_ID = "obstetric_id";
	public static final String COLUMN_OBSTETRIC_PREGNANCY_TRIMESTER = "obstetric_pregnancy_trimester";
	public static final String COLUMN_OBSTETRIC_PLACENTA_PREVIA = "obstetric_placenta_previa";
	public static final String COLUMN_OBSTETRIC_OTHER_RELATIVE_CRITICAL = "obstetric_other_relative_critical";
	public static final String COLUMN_OBSTETRIC_OTHER_NOT_CRITICAL = "obstetric_not_critical";

	public static final String COLUMN_OTHER_PRESENT_CRITICAL_CONDITION = "other_present_critical_condition";
	public static final String COLUMN_OTHER_PRESENT_CRITICAL_CONDITION_ID = "other_id";

	/** Anamnesis Columns */
	public static final String COLUMN_CEREBROVASCULAR_INCIDENTS = "cerebrovascular_incidents";
	public static final String COLUMN_CEREBROVASCULAR_INCIDENTS_ID = "cerebrovascular_incidents_id";
	public static final String COLUMN_CEREBROVASCULAR_INTRACRANIAL_HEMORRHAGE = "cerebrovascular_intracranial_hemorrhage";
	public static final String COLUMN_CEREBROVASCULAR_SAH = "cerebrovascular_SAH";
	public static final String COLUMN_CEREBROVASCULAR_WIDE_STROKE = "cerebrovascular_wide_stroke";
	public static final String COLUMN_CEREBROVASCULAR_MINOR_STROKE = "cerebrovascular_minor_stroke";
	public static final String COLUMN_CEREBROVASCULAR_HEAD_TRAUMA = "cerebrovascular_head_trauma";
	public static final String COLUMN_CEREBROVASCULAR_OTHER_CRITICAL = "cerebrovascular_other_critical";
	public static final String COLUMN_CEREBROVASCULAR_OTHER_NOT_CRITICAL = "cerebrovascular_other_not_critical";

	public static final String COLUMN_DISEASE = "disease";
	public static final String COLUMN_DISEASE_ID = "disease_id";
	public static final String COLUMN_DISEASE_HEPATOPATHY = "disease_hepatopathy";
	public static final String COLUMN_DISEASE_RETINOPATHY = "disease_retinopathy";
	public static final String COLUMN_DISEASE_PANCREATITIS = "disease_pancreatitis";
	public static final String COLUMN_DISEASE_TUMOUR = "disease_tumour";
	public static final String COLUMN_DISEASE_OTHER_CRITICAL = "disease_other_critical";
	public static final String COLUMN_DISEASE_OTHER_NOT_CRITICAL = "disease_other_not_critical";

	public static final String COLUMN_BLEEDING = "bleeding";
	public static final String COLUMN_BLEEDING_ID = "bleeding_id";
	public static final String COLUMN_BLEEDING_TRAUMA = "bleeding_trauma";
	public static final String COLUMN_BLEEDING_ULCUS = "bleeding_ulcus";
	public static final String COLUMN_BLEEDING_VARICES = "bleeding_varices";
	public static final String COLUMN_BLEEDING_OTHER = "bleeding_other";
	public static final String COLUMN_BLEEDING_OTHER_GI = "bleeding_other_GI";
	public static final String COLUMN_BLEEDING_URINARY = "bleeding_other_urinary";
	public static final String COLUMN_BLEEDING_OTHER_NOT_CRITICAL = "bleeding_other_not_critical";

	public static final String COLUMN_OPERATION = "operation";
	public static final String COLUMN_OPERATION_ID = "operation_id";
	public static final String COLUMN_OPERATION_SURGICAL = "operation_surgical";
	public static final String COLUMN_OPERATION_NEUROSURGICAL = "operation_neurosurgical";
	public static final String COLUMN_OPERATION_OPTHALMOLOGICAL = "operation_opthalmological";
	public static final String COLUMN_OPERATION_VESSEL_PUNCTION = "operation_vessel_punction";
	public static final String COLUMN_OPERATION_MASSAGE = "operation_massage";
	public static final String COLUMN_OPERATION_PARTURITION = "operation_parturition";
	public static final String COLUMN_OPERATION_OTHER_NOT_CRITICAL = "operation_other_not_critical";
	public static final String COLUMN_OPERATION_OTHER_CRITICAL = "operation_other_critical";

	public static final String COLUMN_VESSEL = "vessel";
	public static final String COLUMN_VESSEL_ID = "vessel_id";
	public static final String COLUMN_VESSEL_MICROANGIOPATHY = "vessel_microangiopathy";
	public static final String COLUMN_VESSEL_ANEURYSM = "vessel_aneurysm";
	public static final String COLUMN_VESSEL_AVM = "vessel_AVM";
	public static final String COLUMN_VESSEL_OTHER_ANOMAL = "vessel_vessel_punction";
	public static final String COLUMN_VESSEL_OTHER_CRITICAL = "vessel_other_critical";
	public static final String COLUMN_VESSEL_OTHER_NOT_CRITICAL = "vessel_other_not_critical";

	public static final String COLUMN_MYOCARDIAL = "myocardial";
	public static final String COLUMN_MYOCARDIAL_ID = "myocardial_id";

	/** Thrombolysis Columns */
	public static final String COLUMN_FINISH_FOLLOW_UP = "followup_finish";
	public static final String COLUMN_FINISH_FOLLOW_UP_READY = "followup_ready";
	public static final String COLUMN_FINISH_FOLLOW_UP_OTHER = "followup_finish_other";
	public static final String COLUMN_ACTILYSE_TOTAL_DOSE = "actilyse_total_dose";
	public static final String COLUMN_ACTILYSE_BOLUS_DOSE = "actilyse_bolus_dose";
	public static final String COLUMN_ACTILYSE_INFUSION_DOSE = "actilyse_infusion_dose";

	public static final String COLUMN_DIRECT_THROMBO_DECISION = "direct_thrombo";
	public static final String COLUMN_DIRECT_THROMBO_REASON_LATER = "direct_data_later";
	public static final String COLUMN_DIRECT_THROMBO_REASON_OTHER = "direct_other";
	
	public static final String COLUMN_THROMBOLYSIS_DECISION = "thrombolysis_decision";
	public static final String COLUMN_THROMBOLYSIS_DECISION_ID = "thrombolysis_decision_id";

	public static final String COLUMN_RR_FOLLOW_UP_TYPE = "followup_type";
	public static final String COLUMN_RR_FOLLOW_UP_TYPE_ID = "followup_type_id";
	public static final String COLUMN_RR_15_SYSTOLIC = "rr_15_systolic";
	public static final String COLUMN_RR_15_DIASTOLIC = "rr_15_diastolic";
	public static final String COLUMN_RR_30_SYSTOLIC = "rr_30_systolic";
	public static final String COLUMN_RR_30_DIASTOLIC = "rr_30_diastolic";
	public static final String COLUMN_RR_1h_SYSTOLIC = "rr_1h_systolic";
	public static final String COLUMN_RR_1h_DIASTOLIC = "rr_1h_diastolic";
	public static final String COLUMN_RR_2h_SYSTOLIC = "rr_2h_systolic";
	public static final String COLUMN_RR_2h_DIASTOLIC = "rr_2h_diastolic";
	public static final String COLUMN_RR_24h_SYSTOLIC = "rr_24h_systolic";
	public static final String COLUMN_RR_24h_DIASTOLIC = "rr_24h_diastolic";

	public static final String COLUMN_CONTROL_CT_NO_VISILE_SIGN = "control_ct_no_visible_sign";
	public static final String COLUMN_CONTROL_CT_HYPER_SIGN = "control_ct_hyper_sign";
	public static final String COLUMN_CONTROL_CT_EARLY_INFARCT_SIGN = "control_ct_early_infarct_sign";
	public static final String COLUMN_CONTROL_CT_BLEEDING = "control_ct_bleeding";
	public static final String COLUMN_CONTROL_CT_ISCHEMIA_LESS = "control_ct_ischemia_less";
	public static final String COLUMN_CONTROL_CT_ISCHEMIA_MORE = "control_ct_ischemia_more";
	public static final String COLUMN_CONTROL_CT_TUMOR = "control_ct_tumor";
	public static final String COLUMN_CONTROL_CT_ABSCESS = "control_ct_abscess";
	public static final String COLUMN_CONTROL_CT_OTHER = "control_ct_other";

	public static final String COLUMN_INTERRUPT_TREATMENT = "interrupt_treat";
	public static final String COLUMN_INTERRUPT_DETERIORATION = "interrupt_deterioration";
	public static final String COLUMN_INTERRUPT_BLEEDING = "interrupt_bleeding";
	public static final String COLUMN_INTERRUPT_ALLERGY = "interrupt_allergy";
	public static final String COLUMN_INTERRUPT_INFUSION_FAIL = "interrupt_infusion_fail";
	public static final String COLUMN_INTERRUPT_APPT_LONGER_THAN_60 = "interrupt_aptt";
	public static final String COLUMN_INTERRUPT_B_TROM_LESS_THAN_100 = "interrupt_b_trom";
	public static final String COLUMN_INTERRUPT_BLOOD_PRESSURE_UNCONTROL = "interrupt_rr";
	public static final String COLUMN_INTERRUPT_OTHER = "interrupt_other";

	/** Process Interruption Table columns */
	public static final String COLUMN_INTERRUPT_PROCESS_TIME = "interrupt_process_time";
	public static final String COLUMN_INTERRUPT_PROCESS_REASON = "interrupt_process_reason";
	public static final String COLUMN_INTERRUPT_DECISION_ONSET = "deci_onset";
	public static final String COLUMN_INTERRUPT_REASON_ONSET = "reason_onset";
	public static final String COLUMN_INTERRUPT_TIME_ONSET = "time_onset";
	public static final String COLUMN_INTERRUPT_DECISION_TERMINAL_ILLNESS = "deci_terminal_ill";
	public static final String COLUMN_INTERRUPT_REASON_TERMINAL_ILLNESS = "reason_terminal_ill";
	public static final String COLUMN_INTERRUPT_TIME_TERMINAL_ILLNESS = "time_terminal_ill";
	public static final String COLUMN_INTERRUPT_DECISION_RR = "deci_rr";
	public static final String COLUMN_INTERRUPT_REASON_RR = "reason_rr";
	public static final String COLUMN_INTERRUPT_TIME_RR = "time_rr";
	public static final String COLUMN_INTERRUPT_DECISION_RR_AFTER = "deci_rr_after";
	public static final String COLUMN_INTERRUPT_REASON_RR_AFTER = "reason_rr_after";
	public static final String COLUMN_INTERRUPT_TIME_RR_AFTER = "time_rr_after";
	public static final String COLUMN_INTERRUPT_DECISION_mRS = "deci_rr_mRS";
	public static final String COLUMN_INTERRUPT_REASON_mRS = "reason_rr_mRS";
	public static final String COLUMN_INTERRUPT_TIME_mRS = "time_rr_mRS";
	public static final String COLUMN_INTERRUPT_DECISION_ADMI_CT = "deci_admin_ct";
	public static final String COLUMN_INTERRUPT_REASON_ADMI_CT = "reason_admin_ct";
	public static final String COLUMN_INTERRUPT_TIME_ADMI_CT = "time_admin_ct";
	public static final String COLUMN_INTERRUPT_DECISION_CTs = "deci_cts";
	public static final String COLUMN_INTERRUPT_REASON_CTs = "reason_cts";
	public static final String COLUMN_INTERRUPT_TIME_CTs = "time_cts";
	public static final String COLUMN_INTERRUPT_DECISION_QUICK_INR = "deci_quick_inr";
	public static final String COLUMN_INTERRUPT_REASON_QUICK_INR = "reason_quick_inr";
	public static final String COLUMN_INTERRUPT_TIME_QUICK_INR = "time_quick_inr";
	public static final String COLUMN_INTERRUPT_DECISION_INR = "deci_inr";
	public static final String COLUMN_INTERRUPT_REASON_INR = "reason_inr";
	public static final String COLUMN_INTERRUPT_TIME_INR = "time_inr";
	public static final String COLUMN_INTERRUPT_DECISION_APTT = "deci_aptt";
	public static final String COLUMN_INTERRUPT_REASON_APTT = "reason_aptt";
	public static final String COLUMN_INTERRUPT_TIME_APTT = "time_aptt";
	public static final String COLUMN_INTERRUPT_DECISION_B_TROM = "deci_b_trom";
	public static final String COLUMN_INTERRUPT_REASON_B_TROM = "reason_b_trom";
	public static final String COLUMN_INTERRUPT_TIME_B_TROM = "time_b_trom";
	public static final String COLUMN_INTERRUPT_DECISION_BGLUC = "deci_bgluc";
	public static final String COLUMN_INTERRUPT_REASON_BGLUC = "reason_bgluc";
	public static final String COLUMN_INTERRUPT_TIME_BGLUC = "time_bgluc";
	public static final String COLUMN_INTERRUPT_DECISION_BGLUC_AFTER = "deci_bgluc_after";
	public static final String COLUMN_INTERRUPT_REASON_BGLUC_AFTER = "reason_bgluc_after";
	public static final String COLUMN_INTERRUPT_TIME_BGLUC_AFTER = "time_bgluc_after";
	public static final String COLUMN_INTERRUPT_DECISION_ACTIVE_BLEED = "deci_active_bleed";
	public static final String COLUMN_INTERRUPT_REASON_ACTIVE_BLEED = "reason_active_bleed";
	public static final String COLUMN_INTERRUPT_TIME_ACTIVE_BLEED = "time_active_bleed";
	public static final String COLUMN_INTERRUPT_DECISION_ANTICOAGULANT = "deci_anticoa";
	public static final String COLUMN_INTERRUPT_REASON_ANTICOAGULANT = "reason_anticoa";
	public static final String COLUMN_INTERRUPT_TIME_ANTICOAGULANT = "time_anticoa";
	public static final String COLUMN_INTERRUPT_DECISION_CARDIOVASCULAR = "deci_cardio";
	public static final String COLUMN_INTERRUPT_REASON_CARDIOVASCULAR = "reason_cardio";
	public static final String COLUMN_INTERRUPT_TIME_CARDIOVASCULAR = "time_cardio";
	public static final String COLUMN_INTERRUPT_DECISION_OBSTETRIC = "deci_obstetric";
	public static final String COLUMN_INTERRUPT_REASON_OBSTETRIC = "reason_obstetric";
	public static final String COLUMN_INTERRUPT_TIME_OBSTETRIC = "time_obstetric";
	public static final String COLUMN_INTERRUPT_DECISION_PRESENT_OTHER = "deci_present_other";
	public static final String COLUMN_INTERRUPT_REASON_PRESENT_OTHER = "reason_present_other";
	public static final String COLUMN_INTERRUPT_TIME_PRESENT_OTHER = "time_present_other";
	public static final String COLUMN_INTERRUPT_DECISION_CEREBROVASCULAR = "deci_cerebrovas";
	public static final String COLUMN_INTERRUPT_REASON_CEREBROVASCULAR = "reason_cerebrovas";
	public static final String COLUMN_INTERRUPT_TIME_CEREBROVASCULAR = "time_cerebrovas";
	public static final String COLUMN_INTERRUPT_DECISION_DISEASE = "deci_disease";
	public static final String COLUMN_INTERRUPT_REASON_DISEASE = "reason_disease";
	public static final String COLUMN_INTERRUPT_TIME_DISEASE = "time_disease";
	public static final String COLUMN_INTERRUPT_DECISION_BLEED = "deci_bleed";
	public static final String COLUMN_INTERRUPT_REASON_BLEED = "reason_bleed";
	public static final String COLUMN_INTERRUPT_TIME_BLEED = "time_bleed";
	public static final String COLUMN_INTERRUPT_DECISION_OPERATION = "deci_operation";
	public static final String COLUMN_INTERRUPT_REASON_OPERATION = "reason_operation";
	public static final String COLUMN_INTERRUPT_TIME_OPERATION = "time_operation";
	public static final String COLUMN_INTERRUPT_DECISION_VESSEL = "deci_vessel";
	public static final String COLUMN_INTERRUPT_REASON_VESSEL = "reason_vessel";
	public static final String COLUMN_INTERRUPT_TIME_VESSEL = "time_vessel";

	/** Contraindication Columns */
	public static final String COLUMN_CONTRAINDICATION = "contraindication";
	public static final String COLUMN_CONTRAINDICATION_TYPE = "contra_type";

	/** SQL statements */
	public static final String SQL_TABLE_CREATE_DOCTOR_USER = "create table "
			+ DataBaseManage.TABLE_DOCTOR_USERS + "(" + COLUMN_ID
			+ " integer primary key autoincrement," + COLUMN_DOCTOR_USERNAME
			+ " text unique," + COLUMN_DOCTOR_PWD + " text);";

	private static final String SQL_TABLE_CREATE_TIMESTAMPS = "create table "
			+ TABLE_TIMESTAMPS + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_LOG_TIME
			+ " text," + COLUMN_DOCTOR_ID + " integer," + COLUMN_PATIENT_ID
			+ " integer," + COLUMN_PREDOOR_TIME + " integer,"
			+ COLUMN_POSTDOOR_TIME + " integer," + COLUMN_ONSET_LOCK
			+ " integer," + COLUMN_ONSET_TIME + " integer," + COLUMN_ONSET_HOUR
			+ " integer," + COLUMN_ONSET_MIN + " integer,"
			+ COLUMN_ONSET_QUALITY + " text," + COLUMN_ONSET_QUALITY_ID
			+ " integer," + COLUMN_ESTIMATE_LOCK + " integer,"
			+ COLUMN_ESTIMATE_ARRIVAL_TIME + " integer,"
			+ COLUMN_ESTIMATE_ARRIVAL_HOUR + " integer,"
			+ COLUMN_ESTIMATE_ARRIVAL_MIN + " integer," + COLUMN_DOOR_TIME
			+ " integer," + COLUMN_CT_BEGIN_TIME + " integer,"
			+ COLUMN_CT_STATEMENT_TIME + " integer,"
			+ COLUMN_THROMBOLYSIS_DECISION_TIME + " integer,"
			+ COLUMN_BOLUS_TIME + " integer," + COLUMN_INFUSION_BEGIN_TIME
			+ " integer," + COLUMN_INFUSION_END_TIME + " integer,"
			+ COLUMN_FOLLOWUP_END_TIME + " integer," + COLUMN_INTERRUPT_TREAT_TIME
			+ " integer);";

	private static final String SQL_TABLE_CREATE_PATIENT = "create table "
			+ TABLE_PATIENTS + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_LOG_TIME
			+ " text," + COLUMN_DOCTOR_ID + " integer,"
			+ COLUMN_ON_HOSPITAL_DATE + " text," + COLUMN_PATIENT_TYPE
			+ " text," + COLUMN_PATIENT_TYPE_ID + " integer,"
			+ COLUMN_BIRTHDAY_LOCK + " integer," + COLUMN_DATE_OF_BIRTH
			+ " text," + COLUMN_BIRTH_DAY + " integer," + COLUMN_BIRTH_MONTH
			+ " integer," + COLUMN_BIRTH_YEAR + " integer," + COLUMN_AGE
			+ " integer," + COLUMN_WEIGHT + " real,"
			+ COLUMN_THROMBOLYSIS_CANDIDATE + " text,"
			+ COLUMN_THROMBOLYSIS_CANDIDATE_ID + " integer," + COLUMN_WHY
			+ " text);";

	private static final String SQL_TABLE_CREATE_SYMPTOMS = "create table "
			+ TABLE_SYMPTOMS + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_LOG_TIME
			+ " text," + COLUMN_DOCTOR_ID + " integer," + COLUMN_PATIENT_ID
			+ " integer," + COLUMN_SYMPTOM_SIDE + " text,"
			+ COLUMN_LIMB_SYMPTOMS + " text," + COLUMN_FACIAL_PALSY_SYMPTOMS
			+ " text," + COLUMN_SPEECH_DISORDER + " text," + COLUMN_CONVULSIONS
			+ " text," + COLUMN_BASILARIS_SYMPTOMS + " text,"
			+ COLUMN_CARE_FOR_SELF + " text," + COLUMN_TERMINAL_DISEASE
			+ " text," + COLUMN_ANTICOAGULANT + " text,"
			+ COLUMN_QUICK_RECOVER_STROKE_SYMPTOMS + " text,"
			+ COLUMN_SYMPTOMS_SUGGEST_SAV + " text,"
			+ COLUMN_MILD_STROKE_SYMPTOMS + " text," + COLUMN_SYMPTOM_SIDE_ID
			+ " integer," + COLUMN_LIMB_SYMPTOMS_ID + " integer,"
			+ COLUMN_FACIAL_PALSY_SYMPTOMS_ID + " integer,"
			+ COLUMN_SPEECH_DISORDER_ID + " integer," + COLUMN_CONVULSIONS_ID
			+ " integer," + COLUMN_BASILARIS_SYMPTOMS_ID + " integer,"
			+ COLUMN_CARE_FOR_SELF_ID + " integer,"
			+ COLUMN_TERMINAL_DISEASE_ID + " integer,"
			+ COLUMN_ANTICOAGULANT_ID + " integer,"
			+ COLUMN_QUICK_RECOVER_STROKE_SYMPTOMS_ID + " integer,"
			+ COLUMN_SYMPTOMS_SUGGEST_SAV_ID + " integer,"
			+ COLUMN_MILD_STROKE_SYMPTOMS_ID + " integer);";

	private static final String SQL_TABLE_CREATE_NIHSS = "create table "
			+ TABLE_NIHSS + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_LOG_TIME
			+ " text," + COLUMN_DOCTOR_ID + " integer," + COLUMN_PATIENT_ID
			+ " integer," + COLUMN_NIHSS_TYPE + " integer,"
			+ COLUMN_1A_CONSCIOUSNESS_LEVEL + " text,"
			+ COLUMN_1A_CONSCIOUSNESS_LEVEL_ID + " integer,"
			+ COLUMN_1A_CONSCIOUSNESS_LEVEL_POINT + " integer,"
			+ COLUMN_1B_LOC_QUESTIONS + " text," + COLUMN_1B_LOC_QUESTIONS_ID
			+ " integer," + COLUMN_1B_LOC_QUESTIONS_POINT + " integer,"
			+ COLUMN_1C_LOC_COMMANDS + " text," + COLUMN_1C_LOC_COMMANDS_ID
			+ " integer," + COLUMN_1C_LOC_COMMANDS_POINT + " integer,"
			+ COLUMN_2_BEST_GAZE + " text," + COLUMN_2_BEST_GAZE_ID
			+ " integer," + COLUMN_2_BEST_GAZE_POINT + " integer,"
			+ COLUMN_3_VISUAL + " text," + COLUMN_3_VISUAL_ID + " integer,"
			+ COLUMN_3_VISUAL_POINT + " integer," + COLUMN_4_FACIAL_PALSY
			+ " text," + COLUMN_4_FACIAL_PALSY_ID + " integer,"
			+ COLUMN_4_FACIAL_PALSY_POINT + " integer,"
			+ COLUMN_5A_MOTOR_ARM_RIGHT + " text,"
			+ COLUMN_5A_MOTOR_ARM_RIGHT_ID + " integer,"
			+ COLUMN_5A_MOTOR_ARM_RIGHT_POINT + " integer,"
			+ COLUMN_5B_MOTOR_ARM_LEFT + " text," + COLUMN_5B_MOTOR_ARM_LEFT_ID
			+ " integer," + COLUMN_5B_MOTOR_ARM_LEFT_POINT + " integer,"
			+ COLUMN_6A_MOTOR_LEG_RIGHT + " text,"
			+ COLUMN_6A_MOTOR_LEG_RIGHT_ID + " integer,"
			+ COLUMN_6A_MOTOR_LEG_RIGHT_POINT + " integer,"
			+ COLUMN_6B_MOTOR_LEG_LEFT + " text," + COLUMN_6B_MOTOR_LEG_LEFT_ID
			+ " integer," + COLUMN_6B_MOTOR_LEG_LEFT_POINT + " integer,"
			+ COLUMN_7_LIMB_ATAXIA + " text," + COLUMN_7_LIMB_ATAXIA_ID
			+ " integer," + COLUMN_7_LIMB_ATAXIA_POINT + " integer,"
			+ COLUMN_8_SENSORY + " text," + COLUMN_8_SENSORY_ID + " integer,"
			+ COLUMN_8_SENSORY_POINT + " integer," + COLUMN_9_BEST_LANGUAGE
			+ " text," + COLUMN_9_BEST_LANGUAGE_ID + " integer,"
			+ COLUMN_9_BEST_LANGUAGE_POINT + " integer," + COLUMN_10_DYSARTHRIA
			+ " text," + COLUMN_10_DYSARTHRIA_ID + " integer,"
			+ COLUMN_10_DYSARTHRIA_POINT + " integer,"
			+ COLUMN_11_EXTINCTION_INATTENTION + " text,"
			+ COLUMN_11_EXTINCTION_INATTENTION_ID + " integer,"
			+ COLUMN_11_EXTINCTION_INATTENTION_POINT + " integer,"
			+ COLUMN_TOTAL + " text);";

	private static final String SQL_TABLE_CREATE_LAB = "create table "
			+ TABLE_LAB + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_LOG_TIME
			+ " text," + COLUMN_DOCTOR_ID + " integer," + COLUMN_PATIENT_ID
			+ " integer," + COLUMN_RR_SYSTOLIC + " integer,"
			+ COLUMN_RR_DIASTOLIC + " integer," + COLUMN_RR_TREAT_DECISION
			+ " integer," + COLUMN_RR_AFTER_SYSTOLIC + " integer,"
			+ COLUMN_RR_AFTER_DIASTOLIC + " integer,"
			+ COLUMN_MODIFIED_RANKIN_SCALE + " text,"
			+ COLUMN_MODIFIED_RANKIN_SCALE_ID + " integer,"
			+ COLUMN_IS_ADMIS_CT_RECOMMEND + " integer,"
			+ COLUMN_ADMIS_CT_NO_VISILE_SIGN + " integer,"
			+ COLUMN_ADMIS_CT_HYPER_SIGN + " integer,"
			+ COLUMN_ADMIS_CT_EARLY_INFARCT_SIGN + " integer,"
			+ COLUMN_ADMIS_CT_BLEEDING + " integer,"
			+ COLUMN_ADMIS_CT_ISCHEMIA_LESS + " integer,"
			+ COLUMN_ADMIS_CT_ISCHEMIA_MORE + " integer,"
			+ COLUMN_ADMIS_CT_TUMOR + " integer," + COLUMN_ADMIS_CT_ABSCESS
			+ " integer," + COLUMN_ADMIS_CT_OTHER + " integer,"
			+ COLUMN_IS_PERFUSION_CT_RECOMMEND + " integer,"
			+ COLUMN_PERFUSION_CT + " text," + COLUMN_PERFUSION_CT_ID
			+ " integer," + COLUMN_PERFUSION_CT_RESULTS + " text,"
			+ COLUMN_PERFUSION_CT_RESULTS_ID + " integer,"
			+ COLUMN_PERFUSION_CT_NOT_DONE_WHY + " text,"
			+ COLUMN_IS_CT_ANGIOGRAPHY_RECOMMEND + " integer,"
			+ COLUMN_CT_ANGIOGRAPHY + " text," + COLUMN_CT_ANGIOGRAPHY_ID
			+ " integer," + COLUMN_CT_ANGIOGRAPHY_RESULTS + " text,"
			+ COLUMN_CT_ANGIOGRAPHY_RESULTS_ID + " integer,"
			+ COLUMN_CT_ANGIOGRAPHY_NOT_DONE_WHY + " text," + COLUMN_QUICK_INR
			+ " real," + COLUMN_INR + " real," + COLUMN_APTT + " integer,"
			+ COLUMN_P_TROMBAI + " integer," + COLUMN_B_TROM + " integer,"
			+ COLUMN_B_GLUC + " real," 
			+ COLUMN_B_GLUC_TREAT_DECISION
			+ " integer,"
			+ COLUMN_B_GLUC_AFTER_BALANCING
			+ " real);";

	private static final String SQL_TABLE_CREATE_PRESENT_CONDITIONS = "create table "
			+ TABLE_PRESENT_CONDITIONS
			+ "("
			+ COLUMN_ID
			+ " integer primary key autoincrement, "
			+ COLUMN_LOG_TIME
			+ " text,"
			+ COLUMN_DOCTOR_ID
			+ " integer,"
			+ COLUMN_PATIENT_ID
			+ " integer,"
			+ COLUMN_ACTIVE_BLEED
			+ " text,"
			+ COLUMN_ACTIVE_BLEED_ID
			+ " integer,"
			+ COLUMN_ANTICOAGULANT_TREAT
			+ " text,"
			+ COLUMN_ANTICOAGULANT_TREAT_ID
			+ " integer,"
			+ COLUMN_ANTICOAGULANT_HEPARIN
			+ " text,"
			+ COLUMN_ANTICOAGULANT_HEPARIN_ID
			+ " integer,"
			+ COLUMN_ANTICOAGULANT_DABIGATRAN
			+ " text,"
			+ COLUMN_ANTICOAGULANT_DABIGATRAN_ID
			+ " integer,"
			+ COLUMN_ANTICOAGULANT_APIKSABAN
			+ " text,"
			+ COLUMN_ANTICOAGULANT_APIKSABAN_ID
			+ " integer,"
			+ COLUMN_CARDIOVASCULAR_INFECTIONS
			+ " text,"
			+ COLUMN_CARDIOVASCULAR_INFECTIONS_ID
			+ " integer,"
			+ COLUMN_CARDIOVASCULAR_ENDOCARDITIS
			+ " integer,"
			+ COLUMN_CARDIOVASCULAR_PERICARDITIS
			+ " integer,"
			+ COLUMN_CARDIOVASCULAR_EMBOLUS
			+ " integer,"
			+ COLUMN_CARDIOVASCULAR_OTHER_CRITICAL
			+ " integer,"
			+ COLUMN_CARDIOVASCULAR_OTHER_NOT_CRITICAL
			+ " integer,"
			+ COLUMN_OBSTETRIC
			+ " text,"
			+ COLUMN_OBSTETRIC_ID
			+ " integer,"
			+ COLUMN_OBSTETRIC_PREGNANCY_TRIMESTER
			+ " integer,"
			+ COLUMN_OBSTETRIC_PLACENTA_PREVIA
			+ " integer,"
			+ COLUMN_OBSTETRIC_OTHER_RELATIVE_CRITICAL
			+ " integer,"
			+ COLUMN_OBSTETRIC_OTHER_NOT_CRITICAL
			+ " integer,"
			+ COLUMN_OTHER_PRESENT_CRITICAL_CONDITION
			+ " text,"
			+ COLUMN_OTHER_PRESENT_CRITICAL_CONDITION_ID + " integer);";

	private static final String SQL_TABLE_CREATE_ANAMNESIS = "create table "
			+ TABLE_ANAMNESIS + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_LOG_TIME
			+ " text," + COLUMN_DOCTOR_ID + " integer," + COLUMN_PATIENT_ID
			+ " integer," + COLUMN_CEREBROVASCULAR_INCIDENTS + " text,"
			+ COLUMN_CEREBROVASCULAR_INCIDENTS_ID + " integer,"
			+ COLUMN_CEREBROVASCULAR_INTRACRANIAL_HEMORRHAGE + " integer,"
			+ COLUMN_CEREBROVASCULAR_SAH + " integer,"
			+ COLUMN_CEREBROVASCULAR_WIDE_STROKE + " integer,"
			+ COLUMN_CEREBROVASCULAR_MINOR_STROKE + " integer,"
			+ COLUMN_CEREBROVASCULAR_HEAD_TRAUMA + " integer,"
			+ COLUMN_CEREBROVASCULAR_OTHER_CRITICAL + " integer,"
			+ COLUMN_CEREBROVASCULAR_OTHER_NOT_CRITICAL + " integer,"
			+ COLUMN_DISEASE + " text," + COLUMN_DISEASE_ID + " integer,"
			+ COLUMN_DISEASE_HEPATOPATHY + " integer,"
			+ COLUMN_DISEASE_RETINOPATHY + " integer,"
			+ COLUMN_DISEASE_PANCREATITIS + " integer," + COLUMN_DISEASE_TUMOUR
			+ " integer," + COLUMN_DISEASE_OTHER_CRITICAL + " integer,"
			+ COLUMN_DISEASE_OTHER_NOT_CRITICAL + " integer," + COLUMN_BLEEDING
			+ " text," + COLUMN_BLEEDING_ID + " integer,"
			+ COLUMN_BLEEDING_TRAUMA + " integer," + COLUMN_BLEEDING_ULCUS
			+ " integer," + COLUMN_BLEEDING_VARICES + " integer,"
			+ COLUMN_BLEEDING_OTHER + " integer," + COLUMN_BLEEDING_OTHER_GI
			+ " integer," + COLUMN_BLEEDING_URINARY + " integer,"
			+ COLUMN_BLEEDING_OTHER_NOT_CRITICAL + " integer,"
			+ COLUMN_OPERATION + " text," + COLUMN_OPERATION_ID + " integer,"
			+ COLUMN_OPERATION_SURGICAL + " integer,"
			+ COLUMN_OPERATION_NEUROSURGICAL + " integer,"
			+ COLUMN_OPERATION_OPTHALMOLOGICAL + " integer,"
			+ COLUMN_OPERATION_VESSEL_PUNCTION + " integer,"
			+ COLUMN_OPERATION_MASSAGE + " integer,"
			+ COLUMN_OPERATION_PARTURITION + " integer,"
			+ COLUMN_OPERATION_OTHER_NOT_CRITICAL + " integer,"
			+ COLUMN_OPERATION_OTHER_CRITICAL + " integer," + COLUMN_VESSEL
			+ " text," + COLUMN_VESSEL_ID + " integer,"
			+ COLUMN_VESSEL_MICROANGIOPATHY + " integer,"
			+ COLUMN_VESSEL_ANEURYSM + " integer," + COLUMN_VESSEL_AVM
			+ " integer," + COLUMN_VESSEL_OTHER_ANOMAL + " integer,"
			+ COLUMN_VESSEL_OTHER_CRITICAL + " integer,"
			+ COLUMN_VESSEL_OTHER_NOT_CRITICAL + " integer,"
			+ COLUMN_MYOCARDIAL + " text," + COLUMN_MYOCARDIAL_ID
			+ " integer);";

	private static final String SQL_TABLE_CREATE_THROMBOLYSIS = "create table "
			+ TABLE_THROMBOLYSIS + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_LOG_TIME
			+ " text," + COLUMN_DOCTOR_ID + " integer," + COLUMN_PATIENT_ID
			+ " integer," 
			+ COLUMN_DIRECT_THROMBO_DECISION + " integer,"
			+ COLUMN_DIRECT_THROMBO_REASON_LATER + " integer,"
			+ COLUMN_DIRECT_THROMBO_REASON_OTHER + " text,"
			+ COLUMN_FINISH_FOLLOW_UP + " integer,"
			+ COLUMN_FINISH_FOLLOW_UP_READY + " integer,"
			+ COLUMN_FINISH_FOLLOW_UP_OTHER + " text,"
			+ COLUMN_ACTILYSE_TOTAL_DOSE + " real,"
			+ COLUMN_ACTILYSE_BOLUS_DOSE + " real,"
			+ COLUMN_ACTILYSE_INFUSION_DOSE + " real,"
			+ COLUMN_THROMBOLYSIS_DECISION + " text,"
			+ COLUMN_THROMBOLYSIS_DECISION_ID + " integer,"
			+ COLUMN_RR_FOLLOW_UP_TYPE + " text," + COLUMN_RR_FOLLOW_UP_TYPE_ID
			+ " integer," + COLUMN_RR_15_SYSTOLIC + " integer,"
			+ COLUMN_RR_15_DIASTOLIC + " integer," + COLUMN_RR_30_SYSTOLIC
			+ " integer," + COLUMN_RR_30_DIASTOLIC + " integer,"
			+ COLUMN_RR_1h_SYSTOLIC + " integer," + COLUMN_RR_1h_DIASTOLIC
			+ " integer," + COLUMN_RR_2h_SYSTOLIC + " integer,"
			+ COLUMN_RR_2h_DIASTOLIC + " integer," + COLUMN_RR_24h_SYSTOLIC
			+ " integer," + COLUMN_RR_24h_DIASTOLIC + " integer,"
			+ COLUMN_CONTROL_CT_NO_VISILE_SIGN + " integer,"
			+ COLUMN_CONTROL_CT_HYPER_SIGN + " integer,"
			+ COLUMN_CONTROL_CT_EARLY_INFARCT_SIGN + " integer,"
			+ COLUMN_CONTROL_CT_BLEEDING + " integer,"
			+ COLUMN_CONTROL_CT_ISCHEMIA_LESS + " integer,"
			+ COLUMN_CONTROL_CT_ISCHEMIA_MORE + " integer,"
			+ COLUMN_CONTROL_CT_TUMOR + " integer," + COLUMN_CONTROL_CT_ABSCESS
			+ " integer," + COLUMN_CONTROL_CT_OTHER + " integer,"
			+ COLUMN_INTERRUPT_TREATMENT + " integer,"
			+ COLUMN_INTERRUPT_DETERIORATION + " integer,"
			+ COLUMN_INTERRUPT_BLEEDING + " integer,"
			+ COLUMN_INTERRUPT_ALLERGY + " integer,"
			+ COLUMN_INTERRUPT_INFUSION_FAIL + " integer,"
			+ COLUMN_INTERRUPT_APPT_LONGER_THAN_60 + " integer,"
			+ COLUMN_INTERRUPT_B_TROM_LESS_THAN_100 + " integer,"
			+ COLUMN_INTERRUPT_BLOOD_PRESSURE_UNCONTROL + " integer,"
			+ COLUMN_INTERRUPT_OTHER + " text);";

	private static final String SQL_TABLE_CREATE_CONTRAINDICATION = "create table "
			+ TABLE_CONTRAINDICATION
			+ "("
			+ COLUMN_ID
			+ " integer primary key autoincrement, "
			+ COLUMN_PATIENT_ID
			+ " integer,"
			+ COLUMN_CONTRAINDICATION
			+ " text,"
			+ COLUMN_CONTRAINDICATION_TYPE + " integer);";

	private static final String SQL_TABLE_CREATE_INTERRUPTION = "create table "
			+ TABLE_INTERRUPTION + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_LOG_TIME
			+ " text," + COLUMN_DOCTOR_ID + " integer," + COLUMN_PATIENT_ID
			+ " integer," 
			+ COLUMN_INTERRUPT_PROCESS_TIME+ " integer,"
			+ COLUMN_INTERRUPT_PROCESS_REASON + " text,"
			+ COLUMN_INTERRUPT_DECISION_ONSET + " integer,"
			+ COLUMN_INTERRUPT_REASON_ONSET + " text,"
			+ COLUMN_INTERRUPT_TIME_ONSET + " integer,"
			+ COLUMN_INTERRUPT_DECISION_TERMINAL_ILLNESS + " integer,"
			+ COLUMN_INTERRUPT_REASON_TERMINAL_ILLNESS + " text,"
			+ COLUMN_INTERRUPT_TIME_TERMINAL_ILLNESS + " integer,"
			+ COLUMN_INTERRUPT_DECISION_RR + " integer,"
			+ COLUMN_INTERRUPT_REASON_RR + " text," + COLUMN_INTERRUPT_TIME_RR
			+ " integer," + COLUMN_INTERRUPT_DECISION_RR_AFTER + " integer,"
			+ COLUMN_INTERRUPT_REASON_RR_AFTER + " text,"
			+ COLUMN_INTERRUPT_TIME_RR_AFTER + " integer,"
			+ COLUMN_INTERRUPT_DECISION_mRS + " integer,"
			+ COLUMN_INTERRUPT_REASON_mRS + " text,"
			+ COLUMN_INTERRUPT_TIME_mRS + " integer,"
			+ COLUMN_INTERRUPT_DECISION_ADMI_CT + " integer,"
			+ COLUMN_INTERRUPT_REASON_ADMI_CT + " text,"
			+ COLUMN_INTERRUPT_TIME_ADMI_CT + " integer,"
			+ COLUMN_INTERRUPT_DECISION_CTs + " integer,"
			+ COLUMN_INTERRUPT_REASON_CTs + " text,"
			+ COLUMN_INTERRUPT_TIME_CTs + " integer,"
			+ COLUMN_INTERRUPT_DECISION_QUICK_INR + " integer,"
			+ COLUMN_INTERRUPT_REASON_QUICK_INR + " text,"
			+ COLUMN_INTERRUPT_TIME_QUICK_INR + " integer,"
			+ COLUMN_INTERRUPT_DECISION_INR + " integer,"
			+ COLUMN_INTERRUPT_REASON_INR + " text,"
			+ COLUMN_INTERRUPT_TIME_INR + " integer,"
			+ COLUMN_INTERRUPT_DECISION_APTT + " integer,"
			+ COLUMN_INTERRUPT_REASON_APTT + " text,"
			+ COLUMN_INTERRUPT_TIME_APTT + " integer,"
			+ COLUMN_INTERRUPT_DECISION_B_TROM + " integer,"
			+ COLUMN_INTERRUPT_REASON_B_TROM + " text,"
			+ COLUMN_INTERRUPT_TIME_B_TROM + " integer,"
			+ COLUMN_INTERRUPT_DECISION_BGLUC + " integer,"
			+ COLUMN_INTERRUPT_REASON_BGLUC + " text,"
			+ COLUMN_INTERRUPT_TIME_BGLUC + " integer,"
			+ COLUMN_INTERRUPT_DECISION_BGLUC_AFTER + " integer,"
			+ COLUMN_INTERRUPT_REASON_BGLUC_AFTER + " text,"
			+ COLUMN_INTERRUPT_TIME_BGLUC_AFTER + " integer,"
			+ COLUMN_INTERRUPT_DECISION_ACTIVE_BLEED + " integer,"
			+ COLUMN_INTERRUPT_REASON_ACTIVE_BLEED + " text,"
			+ COLUMN_INTERRUPT_TIME_ACTIVE_BLEED + " integer,"
			+ COLUMN_INTERRUPT_DECISION_ANTICOAGULANT + " integer,"
			+ COLUMN_INTERRUPT_REASON_ANTICOAGULANT + " text,"
			+ COLUMN_INTERRUPT_TIME_ANTICOAGULANT + " integer,"
			+ COLUMN_INTERRUPT_DECISION_CARDIOVASCULAR + " integer,"
			+ COLUMN_INTERRUPT_REASON_CARDIOVASCULAR + " text,"
			+ COLUMN_INTERRUPT_TIME_CARDIOVASCULAR + " integer,"
			+ COLUMN_INTERRUPT_DECISION_OBSTETRIC + " integer,"
			+ COLUMN_INTERRUPT_REASON_OBSTETRIC + " text,"
			+ COLUMN_INTERRUPT_TIME_OBSTETRIC + " integer,"
			+ COLUMN_INTERRUPT_DECISION_PRESENT_OTHER + " integer,"
			+ COLUMN_INTERRUPT_REASON_PRESENT_OTHER + " text,"
			+ COLUMN_INTERRUPT_TIME_PRESENT_OTHER + " integer,"
			+ COLUMN_INTERRUPT_DECISION_CEREBROVASCULAR + " integer,"
			+ COLUMN_INTERRUPT_REASON_CEREBROVASCULAR + " text,"
			+ COLUMN_INTERRUPT_TIME_CEREBROVASCULAR + " integer,"
			+ COLUMN_INTERRUPT_DECISION_DISEASE + " integer,"
			+ COLUMN_INTERRUPT_REASON_DISEASE + " text,"
			+ COLUMN_INTERRUPT_TIME_DISEASE + " integer,"
			+ COLUMN_INTERRUPT_DECISION_BLEED + " integer,"
			+ COLUMN_INTERRUPT_REASON_BLEED + " text,"
			+ COLUMN_INTERRUPT_TIME_BLEED + " integer,"
			+ COLUMN_INTERRUPT_DECISION_OPERATION + " integer,"
			+ COLUMN_INTERRUPT_REASON_OPERATION + " text,"
			+ COLUMN_INTERRUPT_TIME_OPERATION + " integer,"
			+ COLUMN_INTERRUPT_DECISION_VESSEL + " integer,"
			+ COLUMN_INTERRUPT_REASON_VESSEL + " text,"
			+ COLUMN_INTERRUPT_TIME_VESSEL + " integer);";

	/** Index for fast query */
	private static final String INDEX_TABLE_TIMESTAMPS = "create index index_timestamps on "
			+ TABLE_TIMESTAMPS + "(" + COLUMN_PATIENT_ID + ");";
	private static final String INDEX_TABLE_PATIENTs = "create index index_patients on "
			+ TABLE_PATIENTS + "(" + COLUMN_PATIENT_ID + ");";
	private static final String INDEX_TABLE_SYMPTOMS = "create index index_symptoms on "
			+ TABLE_SYMPTOMS + "(" + COLUMN_PATIENT_ID + ");";
	private static final String INDEX_TABLE_ANAMNESIS = "create index index_anamnesis on "
			+ TABLE_ANAMNESIS + "(" + COLUMN_PATIENT_ID + ");";
	private static final String INDEX_TABLE_PRESENT_CONDITIONS = "create index index_present_condition on "
			+ TABLE_PRESENT_CONDITIONS + "(" + COLUMN_PATIENT_ID + ");";
	private static final String INDEX_TABLE_LAB = "create index index_lab on "
			+ TABLE_LAB + "(" + COLUMN_PATIENT_ID + ");";
	private static final String INDEX_TABLE_NIHSS = "create index index_nihss on "
			+ TABLE_NIHSS + "(" + COLUMN_PATIENT_ID + ");";
	private static final String INDEX_TABLE_THROMBOLYSIS = "create index index_thrombolysis on "
			+ TABLE_THROMBOLYSIS + "(" + COLUMN_PATIENT_ID + ");";

	public DataBaseManage(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_TABLE_CREATE_DOCTOR_USER);
		db.execSQL(SQL_TABLE_CREATE_TIMESTAMPS);
		db.execSQL(SQL_TABLE_CREATE_PATIENT);
		db.execSQL(SQL_TABLE_CREATE_SYMPTOMS);
		db.execSQL(SQL_TABLE_CREATE_NIHSS);
		db.execSQL(SQL_TABLE_CREATE_LAB);
		db.execSQL(SQL_TABLE_CREATE_PRESENT_CONDITIONS);
		db.execSQL(SQL_TABLE_CREATE_ANAMNESIS);
		db.execSQL(SQL_TABLE_CREATE_THROMBOLYSIS);
		db.execSQL(SQL_TABLE_CREATE_CONTRAINDICATION);
		db.execSQL(SQL_TABLE_CREATE_INTERRUPTION);
		//
		//
		//
		//
		// db.execSQL(INDEX_TABLE_ANAMNESIS);
		// db.execSQL(INDEX_TABLE_LAB);
		// db.execSQL(INDEX_TABLE_NIHSS);
		// db.execSQL(INDEX_TABLE_PATIENTs);
		// db.execSQL(INDEX_TABLE_PRE_NOTIFICATION);
		// db.execSQL(INDEX_TABLE_PRESENT_CONDITIONS);
		// db.execSQL(INDEX_TABLE_SYMPTOMS);
		// db.execSQL(INDEX_TABLE_THROMBOLYSIS);
		// db.execSQL(INDEX_TABLE_TIMESTAMPS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public static void backupDbToSDCard() {
		try {
			File sd = Environment.getExternalStorageDirectory();
			File data = Environment.getDataDirectory();

			if (sd.canWrite()) {
				String currentDBPath = "//data//fi.etla.strokehelper//databases//"
						+ DATABASE_NAME;
				String backupDBPath = DATABASE_NAME;
				File currentDB = new File(data, currentDBPath);
				File backupDB = new File(sd, backupDBPath);

				FileChannel src = new FileInputStream(currentDB).getChannel();
				FileChannel dst = new FileOutputStream(backupDB).getChannel();
				dst.transferFrom(src, 0, src.size());
				src.close();
				dst.close();

			}
		} catch (Exception e) {

		}
	}
}
