/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizappv3.utils;

import schoolexc.quizappv3.services.BaseService;
import schoolexc.quizappv3.services.CategoryServices;
import schoolexc.quizappv3.services.LevelServices;
import schoolexc.quizappv3.services.UpdQuestionServices;
import schoolexc.quizappv3.services.question.BaseQuestionService;
import schoolexc.quizappv3.services.question.QuestionService;

/**
 *
 * @author LE TUNG
 */
public class Configs {

    public static CategoryServices cateService = new CategoryServices();
    public static LevelServices lvlService = new LevelServices();
    public static UpdQuestionServices updQuestionService = new UpdQuestionServices();
    public static BaseQuestionService questionService = new QuestionService();
}
