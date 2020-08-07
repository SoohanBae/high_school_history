package soohan530.google.com.jjoji_project.DataBean

data class Get_answer_exam_list(var examQNo : Int,
                                var examQNumber : Int,
                                var examQName : String,
                                var examQImage : String,
                                var examARight : String,
                                var examNo : Int,
                                var examASlot : ArrayList<ExamASlot>)