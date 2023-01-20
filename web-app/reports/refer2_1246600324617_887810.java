/*
 * Generated by JasperReports - 7/3/09 12:52 PM
 */
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.fill.*;

import java.util.*;
import java.math.*;
import java.text.*;
import java.io.*;
import java.net.*;

import net.sf.jasperreports.engine.*;
import java.util.*;
import net.sf.jasperreports.engine.data.*;


/**
 *
 */
public class refer2_1246600324617_887810 extends JREvaluator
{


    /**
     *
     */
    private JRFillParameter parameter_tambon = null;
    private JRFillParameter parameter_to = null;
    private JRFillParameter parameter_sex = null;
    private JRFillParameter parameter_examination = null;
    private JRFillParameter parameter_cause = null;
    private JRFillParameter parameter_REPORT_TIME_ZONE = null;
    private JRFillParameter parameter_amphur = null;
    private JRFillParameter parameter_no = null;
    private JRFillParameter parameter_treatment = null;
    private JRFillParameter parameter_diagnosis = null;
    private JRFillParameter parameter_REPORT_PARAMETERS_MAP = null;
    private JRFillParameter parameter_road = null;
    private JRFillParameter parameter_date = null;
    private JRFillParameter parameter_REPORT_CLASS_LOADER = null;
    private JRFillParameter parameter_REPORT_URL_HANDLER_FACTORY = null;
    private JRFillParameter parameter_REPORT_DATA_SOURCE = null;
    private JRFillParameter parameter_IS_IGNORE_PAGINATION = null;
    private JRFillParameter parameter_doctor = null;
    private JRFillParameter parameter_historynow = null;
    private JRFillParameter parameter_patient = null;
    private JRFillParameter parameter_police = null;
    private JRFillParameter parameter_REPORT_MAX_COUNT = null;
    private JRFillParameter parameter_REPORT_TEMPLATES = null;
    private JRFillParameter parameter_age = null;
    private JRFillParameter parameter_province = null;
    private JRFillParameter parameter_REPORT_LOCALE = null;
    private JRFillParameter parameter_ban = null;
    private JRFillParameter parameter_REPORT_VIRTUALIZER = null;
    private JRFillParameter parameter_moo = null;
    private JRFillParameter parameter_REPORT_SCRIPTLET = null;
    private JRFillParameter parameter_REPORT_CONNECTION = null;
    private JRFillParameter parameter_for1 = null;
    private JRFillParameter parameter_for2 = null;
    private JRFillParameter parameter_for3 = null;
    private JRFillParameter parameter_for4 = null;
    private JRFillParameter parameter_historypass = null;
    private JRFillParameter parameter_REPORT_FORMAT_FACTORY = null;
    private JRFillParameter parameter_near = null;
    private JRFillParameter parameter_nopolice = null;
    private JRFillParameter parameter_comment = null;
    private JRFillParameter parameter_REPORT_RESOURCE_BUNDLE = null;
    private JRFillVariable variable_PAGE_NUMBER = null;
    private JRFillVariable variable_COLUMN_NUMBER = null;
    private JRFillVariable variable_REPORT_COUNT = null;
    private JRFillVariable variable_PAGE_COUNT = null;
    private JRFillVariable variable_COLUMN_COUNT = null;


    /**
     *
     */
    public void customizedInit(
        Map pm,
        Map fm,
        Map vm
        )
    {
        initParams(pm);
        initFields(fm);
        initVars(vm);
    }


    /**
     *
     */
    private void initParams(Map pm)
    {
        parameter_tambon = (JRFillParameter)pm.get("tambon");
        parameter_to = (JRFillParameter)pm.get("to");
        parameter_sex = (JRFillParameter)pm.get("sex");
        parameter_examination = (JRFillParameter)pm.get("examination");
        parameter_cause = (JRFillParameter)pm.get("cause");
        parameter_REPORT_TIME_ZONE = (JRFillParameter)pm.get("REPORT_TIME_ZONE");
        parameter_amphur = (JRFillParameter)pm.get("amphur");
        parameter_no = (JRFillParameter)pm.get("no");
        parameter_treatment = (JRFillParameter)pm.get("treatment");
        parameter_diagnosis = (JRFillParameter)pm.get("diagnosis");
        parameter_REPORT_PARAMETERS_MAP = (JRFillParameter)pm.get("REPORT_PARAMETERS_MAP");
        parameter_road = (JRFillParameter)pm.get("road");
        parameter_date = (JRFillParameter)pm.get("date");
        parameter_REPORT_CLASS_LOADER = (JRFillParameter)pm.get("REPORT_CLASS_LOADER");
        parameter_REPORT_URL_HANDLER_FACTORY = (JRFillParameter)pm.get("REPORT_URL_HANDLER_FACTORY");
        parameter_REPORT_DATA_SOURCE = (JRFillParameter)pm.get("REPORT_DATA_SOURCE");
        parameter_IS_IGNORE_PAGINATION = (JRFillParameter)pm.get("IS_IGNORE_PAGINATION");
        parameter_doctor = (JRFillParameter)pm.get("doctor");
        parameter_historynow = (JRFillParameter)pm.get("historynow");
        parameter_patient = (JRFillParameter)pm.get("patient");
        parameter_police = (JRFillParameter)pm.get("police");
        parameter_REPORT_MAX_COUNT = (JRFillParameter)pm.get("REPORT_MAX_COUNT");
        parameter_REPORT_TEMPLATES = (JRFillParameter)pm.get("REPORT_TEMPLATES");
        parameter_age = (JRFillParameter)pm.get("age");
        parameter_province = (JRFillParameter)pm.get("province");
        parameter_REPORT_LOCALE = (JRFillParameter)pm.get("REPORT_LOCALE");
        parameter_ban = (JRFillParameter)pm.get("ban");
        parameter_REPORT_VIRTUALIZER = (JRFillParameter)pm.get("REPORT_VIRTUALIZER");
        parameter_moo = (JRFillParameter)pm.get("moo");
        parameter_REPORT_SCRIPTLET = (JRFillParameter)pm.get("REPORT_SCRIPTLET");
        parameter_REPORT_CONNECTION = (JRFillParameter)pm.get("REPORT_CONNECTION");
        parameter_for1 = (JRFillParameter)pm.get("for1");
        parameter_for2 = (JRFillParameter)pm.get("for2");
        parameter_for3 = (JRFillParameter)pm.get("for3");
        parameter_for4 = (JRFillParameter)pm.get("for4");
        parameter_historypass = (JRFillParameter)pm.get("historypass");
        parameter_REPORT_FORMAT_FACTORY = (JRFillParameter)pm.get("REPORT_FORMAT_FACTORY");
        parameter_near = (JRFillParameter)pm.get("near");
        parameter_nopolice = (JRFillParameter)pm.get("nopolice");
        parameter_comment = (JRFillParameter)pm.get("comment");
        parameter_REPORT_RESOURCE_BUNDLE = (JRFillParameter)pm.get("REPORT_RESOURCE_BUNDLE");
    }


    /**
     *
     */
    private void initFields(Map fm)
    {
    }


    /**
     *
     */
    private void initVars(Map vm)
    {
        variable_PAGE_NUMBER = (JRFillVariable)vm.get("PAGE_NUMBER");
        variable_COLUMN_NUMBER = (JRFillVariable)vm.get("COLUMN_NUMBER");
        variable_REPORT_COUNT = (JRFillVariable)vm.get("REPORT_COUNT");
        variable_PAGE_COUNT = (JRFillVariable)vm.get("PAGE_COUNT");
        variable_COLUMN_COUNT = (JRFillVariable)vm.get("COLUMN_COUNT");
    }


    /**
     *
     */
    public Object evaluate(int id) throws Throwable
    {
        Object value = null;

        switch (id)
        {
            case 0 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=0$
                break;
            }
            case 1 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=1$
                break;
            }
            case 2 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=2$
                break;
            }
            case 3 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=3$
                break;
            }
            case 4 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=4$
                break;
            }
            case 5 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=5$
                break;
            }
            case 6 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=6$
                break;
            }
            case 7 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=7$
                break;
            }
            case 8 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_patient.getValue()));//$JR_EXPR_ID=8$
                break;
            }
            case 9 : 
            {
                value = (java.lang.String)("( "+((java.lang.String)parameter_doctor.getValue())+" )");//$JR_EXPR_ID=9$
                break;
            }
            case 10 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_no.getValue()));//$JR_EXPR_ID=10$
                break;
            }
            case 11 : 
            {
                value = (java.util.Date)(((java.util.Date)parameter_date.getValue()));//$JR_EXPR_ID=11$
                break;
            }
            case 12 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_comment.getValue()));//$JR_EXPR_ID=12$
                break;
            }
            case 13 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_examination.getValue()));//$JR_EXPR_ID=13$
                break;
            }
            case 14 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_diagnosis.getValue()));//$JR_EXPR_ID=14$
                break;
            }
            case 15 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_treatment.getValue()));//$JR_EXPR_ID=15$
                break;
            }
            case 16 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_to.getValue()));//$JR_EXPR_ID=16$
                break;
            }
            case 17 : 
            {
                value = (java.lang.String)((((java.lang.Integer)parameter_sex.getValue()).intValue() == 1)?"???":"????");//$JR_EXPR_ID=17$
                break;
            }
            case 18 : 
            {
                value = (java.lang.Integer)(((java.lang.Integer)parameter_age.getValue()));//$JR_EXPR_ID=18$
                break;
            }
            case 19 : 
            {
                value = (java.util.Date)(((java.util.Date)parameter_date.getValue()));//$JR_EXPR_ID=19$
                break;
            }
           default :
           {
           }
        }
        
        return value;
    }


    /**
     *
     */
    public Object evaluateOld(int id) throws Throwable
    {
        Object value = null;

        switch (id)
        {
            case 0 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=0$
                break;
            }
            case 1 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=1$
                break;
            }
            case 2 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=2$
                break;
            }
            case 3 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=3$
                break;
            }
            case 4 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=4$
                break;
            }
            case 5 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=5$
                break;
            }
            case 6 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=6$
                break;
            }
            case 7 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=7$
                break;
            }
            case 8 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_patient.getValue()));//$JR_EXPR_ID=8$
                break;
            }
            case 9 : 
            {
                value = (java.lang.String)("( "+((java.lang.String)parameter_doctor.getValue())+" )");//$JR_EXPR_ID=9$
                break;
            }
            case 10 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_no.getValue()));//$JR_EXPR_ID=10$
                break;
            }
            case 11 : 
            {
                value = (java.util.Date)(((java.util.Date)parameter_date.getValue()));//$JR_EXPR_ID=11$
                break;
            }
            case 12 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_comment.getValue()));//$JR_EXPR_ID=12$
                break;
            }
            case 13 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_examination.getValue()));//$JR_EXPR_ID=13$
                break;
            }
            case 14 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_diagnosis.getValue()));//$JR_EXPR_ID=14$
                break;
            }
            case 15 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_treatment.getValue()));//$JR_EXPR_ID=15$
                break;
            }
            case 16 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_to.getValue()));//$JR_EXPR_ID=16$
                break;
            }
            case 17 : 
            {
                value = (java.lang.String)((((java.lang.Integer)parameter_sex.getValue()).intValue() == 1)?"???":"????");//$JR_EXPR_ID=17$
                break;
            }
            case 18 : 
            {
                value = (java.lang.Integer)(((java.lang.Integer)parameter_age.getValue()));//$JR_EXPR_ID=18$
                break;
            }
            case 19 : 
            {
                value = (java.util.Date)(((java.util.Date)parameter_date.getValue()));//$JR_EXPR_ID=19$
                break;
            }
           default :
           {
           }
        }
        
        return value;
    }


    /**
     *
     */
    public Object evaluateEstimated(int id) throws Throwable
    {
        Object value = null;

        switch (id)
        {
            case 0 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=0$
                break;
            }
            case 1 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=1$
                break;
            }
            case 2 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=2$
                break;
            }
            case 3 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=3$
                break;
            }
            case 4 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=4$
                break;
            }
            case 5 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=5$
                break;
            }
            case 6 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=6$
                break;
            }
            case 7 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=7$
                break;
            }
            case 8 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_patient.getValue()));//$JR_EXPR_ID=8$
                break;
            }
            case 9 : 
            {
                value = (java.lang.String)("( "+((java.lang.String)parameter_doctor.getValue())+" )");//$JR_EXPR_ID=9$
                break;
            }
            case 10 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_no.getValue()));//$JR_EXPR_ID=10$
                break;
            }
            case 11 : 
            {
                value = (java.util.Date)(((java.util.Date)parameter_date.getValue()));//$JR_EXPR_ID=11$
                break;
            }
            case 12 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_comment.getValue()));//$JR_EXPR_ID=12$
                break;
            }
            case 13 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_examination.getValue()));//$JR_EXPR_ID=13$
                break;
            }
            case 14 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_diagnosis.getValue()));//$JR_EXPR_ID=14$
                break;
            }
            case 15 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_treatment.getValue()));//$JR_EXPR_ID=15$
                break;
            }
            case 16 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_to.getValue()));//$JR_EXPR_ID=16$
                break;
            }
            case 17 : 
            {
                value = (java.lang.String)((((java.lang.Integer)parameter_sex.getValue()).intValue() == 1)?"???":"????");//$JR_EXPR_ID=17$
                break;
            }
            case 18 : 
            {
                value = (java.lang.Integer)(((java.lang.Integer)parameter_age.getValue()));//$JR_EXPR_ID=18$
                break;
            }
            case 19 : 
            {
                value = (java.util.Date)(((java.util.Date)parameter_date.getValue()));//$JR_EXPR_ID=19$
                break;
            }
           default :
           {
           }
        }
        
        return value;
    }


}
