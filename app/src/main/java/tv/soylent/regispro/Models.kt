package tv.soylent.regispro

class School{
    var schoolID : Int = 0
    var schoolName : String? = ""

}

class Subject{
    var subjectID : Int = 0
    var subjectName : String? = ""
}

class Klass{
    var classID : Int = 0
    var className : String? = ""
    var schoolID : Int = 0
}

class Student{
    var studentID : Int = 0
    var studentName : String? = ""
    var studentSurname : String? = ""
    var schoolID : Int = 0
    var classID : Int = 0
}