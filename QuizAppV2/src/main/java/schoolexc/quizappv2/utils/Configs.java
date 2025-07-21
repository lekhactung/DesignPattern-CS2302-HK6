/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizappv2.utils;

import schoolexc.quizappv2.services.CategoryServices;
import schoolexc.quizappv2.services.LevelServices;
import schoolexc.quizappv2.services.question.QuestionServices;
import schoolexc.quizappv2.services.UpdateQuestionService;
import schoolexc.quizappv2.services.question.BaseQuestionService;

/**
 *
 * @author LE TUNG
 */
public class Configs {
    public static final CategoryServices cateServices = new CategoryServices();
    public static final LevelServices levelServices = new LevelServices();
    public static BaseQuestionService questionServices = new QuestionServices();
    public static UpdateQuestionService updQuestionService = new UpdateQuestionService();
    public static final int NUM_OF_QUESTION = 10;
    public static boolean LOGIN_STATUS = false;
}
