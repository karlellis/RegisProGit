package tv.soylent.regispro

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import java.lang.Exception

class DBHandler (context: Context, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) :
    SQLiteOpenHelper (context, DATABASE_NAME, factory, DATABASE_VERSION) {

    companion object {
        private val DATABASE_NAME = "regispro.db"
        private val DATABASE_VERSION = 1

        val SCHOOL_TABLE = "School"
        val COLUMN_SCHOOLID = "schoolid"
        val COLUMN_SCHOOLNAME = "schoolname"

        val SUBJECT_TABLE = "Subject"
        val COLUMN_SUBJECTID = "subjectid"
        val COLUMN_SUBJECTNAME = "subjectname"

        val CLASS_TABLE = "Klass"
        val COLUMN_CLASSID = "classtid"
        //val COLUMN_FK_SCHOOLID = "schoolid"
        val COLUMN_CLASSNAME = "classtname"

        val STUDENT_TABLE = "Student"
        val COLUMN_STUDENTID = "studentid"
        //val COLUMN_FK_SCHOOLID = "schoolid"
        val COLUMN_STUDENTNAME = "studentname"
        val COLUMN_STUDENTSURNAME = "studentsurname"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        val CREATE_SCHOOL_TABLE: String = ("CREATE TABLE $SCHOOL_TABLE (" +
                "$COLUMN_SCHOOLID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_SCHOOLNAME TEXT)")

        val CREATE_SUBJECT_TABLE: String = ("CREATE TABLE $SUBJECT_TABLE (" +
                "$COLUMN_SUBJECTID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_SUBJECTNAME TEXT)")

        val CREATE_CLASS_TABLE: String = ("CREATE TABLE $CLASS_TABLE (" +
                "$COLUMN_CLASSID INTEGER PRIMARY KEY AUTOINCREMENT," +
//                "$COLUMN_SCHOOLID INTEGER," +
                "$COLUMN_CLASSNAME TEXT," +
                "FOREIGN KEY (" + COLUMN_SCHOOLID + ") REFERENCES " +
                SCHOOL_TABLE + "(" + COLUMN_SCHOOLID + "))")

        val CREATE_STUDENT_TABLE: String = ("CREATE TABLE $STUDENT_TABLE (" +
                "$COLUMN_STUDENTID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_STUDENTNAME INTEGER," +
                "$COLUMN_STUDENTSURNAME INTEGER," +
                "FOREIGN KEY (" + COLUMN_SCHOOLID + ") REFERENCES " +
                SCHOOL_TABLE + "(" + COLUMN_SCHOOLID + ")," +
                "FOREIGN KEY (" + COLUMN_CLASSID + ") REFERENCES " +
                CLASS_TABLE + "(" + COLUMN_CLASSID + "))")

        db?.execSQL(CREATE_SCHOOL_TABLE)

        db?.execSQL(CREATE_SUBJECT_TABLE)

        db?.execSQL(CREATE_CLASS_TABLE)

        db?.execSQL(CREATE_STUDENT_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
//        db?.execSQL("DROP TABLE IF EXISTS $SCHOOL_TABLE")
//        db?.execSQL("DROP TABLE IF EXISTS $SUBJECT_TABLE")
//        db?.execSQL("DROP TABLE IF EXISTS $CLASS_TABLE")
//        db?.execSQL("DROP TABLE IF EXISTS $STUDENT_TABLE")
//        onCreate(db)

//        if (oldVersion < 2) {
//            db.execSQL("Alter Table $SCHOOL_TABLE" +
//            "Add $COLUMN_NEWITEM TEXT NULL")
//        }
    }

    fun getSchools(mCtx: Context): ArrayList<School> {
        val qry = "Select * From $SCHOOL_TABLE"
        val db: SQLiteDatabase = this.readableDatabase
        val cursor: Cursor = db.rawQuery(qry, null)
        val schools = ArrayList<School>()

        if (cursor.count == 0)
            Toast.makeText(mCtx, "Nessun Istituto trovato", Toast.LENGTH_SHORT).show() else {
            cursor.moveToFirst()
            while (!cursor.isAfterLast()) {
                val school = School()
                school.schoolID = cursor.getInt(cursor.getColumnIndex(COLUMN_SCHOOLID))
                school.schoolName = cursor.getString(cursor.getColumnIndex(COLUMN_SCHOOLNAME))
                schools.add(school)
                cursor.moveToNext()
            }
            if (mCtx != null)
                Toast.makeText(mCtx, "${cursor.count} Istituti trovati", Toast.LENGTH_SHORT).show()
        }
        cursor.close()
        db.close()
        return schools
    }

    fun addSchool(mCtx: Context, school: School) {
        val values = ContentValues()
        values.put(COLUMN_SCHOOLNAME, school.schoolName)
        val db = this.writableDatabase
        try {
            db.insert(SCHOOL_TABLE, null, values)
//            db.rawQuery("Insert Into $SCHOOL_TABLE ($COLUMN_SCHOOLNAME) Values()")
            Toast.makeText(mCtx, "Istituto Aggiunto", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(mCtx, e.message, Toast.LENGTH_SHORT).show()
        }
        db.close()
    }

    fun deleteSchool(schoolID: Int): Boolean {
        val qry = "Delete From $SCHOOL_TABLE where $COLUMN_SCHOOLID = $schoolID"
        val db = this.writableDatabase
        var result: Boolean = false
        try {
//            val cursor = db.delete(SCHOOL_TABLE, "$COLUMN_SCHOOLID = ?", arrayOf(schoolID.toString()))
            val cursor = db.execSQL(qry)
            result = true
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Errore nella cancellazione")
        }
        db.close()
        return result
    }

    fun updateSchool(id: String, schoolName: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        var result: Boolean = false
        contentValues.put(COLUMN_SCHOOLNAME, schoolName)
        try {
            db.update(SCHOOL_TABLE, contentValues, "$COLUMN_SCHOOLID = ?", arrayOf(id))
            result = true
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Errore nell'aggiornamento")
            result = false
        }
        return result
    }

    fun getSubjects(mCtx: Context): ArrayList<Subject> {
        val qry = "Select * From $SUBJECT_TABLE"
        val db: SQLiteDatabase = this.readableDatabase
        val cursor: Cursor = db.rawQuery(qry, null)
        val subjects = ArrayList<Subject>()

        if (cursor.count == 0)
            Toast.makeText(mCtx, "Nessuna Materia trovata", Toast.LENGTH_SHORT).show() else {
            cursor.moveToFirst()
            while (!cursor.isAfterLast()) {
                val subject = Subject()
                subject.subjectID = cursor.getInt(cursor.getColumnIndex(COLUMN_SUBJECTID))
                subject.subjectName = cursor.getString(cursor.getColumnIndex(COLUMN_SUBJECTNAME))
                subjects.add(subject)
                cursor.moveToNext()
            }
            Toast.makeText(mCtx, "${cursor.count.toString()} Materie trovate", Toast.LENGTH_SHORT).show()
        }
        cursor.close()
        db.close()
        return subjects
    }

    fun addSubject(mCtx: Context, subject: Subject) {
        val values = ContentValues()
        values.put(COLUMN_SUBJECTNAME, subject.subjectName)
        val db = this.writableDatabase
        try {
            db.insert(SUBJECT_TABLE, null, values)
            Toast.makeText(mCtx, "Materia aggiunta", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(mCtx, e.message, Toast.LENGTH_SHORT).show()
        }
        db.close()
    }

    fun deleteSubject(subjectID: Int): Boolean {
        val qry = "Delete From $SUBJECT_TABLE where $COLUMN_SUBJECTID = $subjectID"
        val db = this.writableDatabase
        var result: Boolean = false
        try {
            val cursor = db.execSQL(qry)
            result = true
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Errore nella cancellazione")
        }
        db.close()
        return result
    }

    fun updateSubject(id: String, subjectName: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        var result: Boolean
        contentValues.put(COLUMN_SUBJECTNAME, subjectName)
        try {
            db.update(SUBJECT_TABLE, contentValues, "$COLUMN_SUBJECTID = ?", arrayOf(id))
            result = true
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Errore nell'aggiornamento")
            result = false
        }
        return result
    }

    fun getClasses(mCtx: Context): ArrayList<Klass> {
        val qry = "Select * From $CLASS_TABLE"
        val db: SQLiteDatabase = this.readableDatabase
        val cursor: Cursor = db.rawQuery(qry, null)
        val classes = ArrayList<Klass>()

        if (cursor.count == 0)
            Toast.makeText(mCtx, "Nessuna Classe trovata", Toast.LENGTH_SHORT).show() else {
            cursor.moveToFirst()
            while (!cursor.isAfterLast()) {
                val klass = Klass()
                klass.classID = cursor.getInt(cursor.getColumnIndex(COLUMN_CLASSID))
                klass.schoolID = cursor.getInt(cursor.getColumnIndex(COLUMN_SCHOOLID))
                klass.className = cursor.getString(cursor.getColumnIndex(COLUMN_CLASSNAME))
                classes.add(klass)
                cursor.moveToNext()
            }
            Toast.makeText(mCtx, "${cursor.count.toString()} Classi trovate", Toast.LENGTH_SHORT).show()
        }
        cursor.close()
        db.close()
        return classes
    }

    fun addClass(mCtx: Context, klass: Klass) {
        val values = ContentValues()
        values.put(COLUMN_CLASSNAME, klass.className)
        values.put(COLUMN_SCHOOLID, klass.schoolID)
        val db = this.writableDatabase
        try {
            db.insert(CLASS_TABLE, null, values)
            Toast.makeText(mCtx, "Classe aggiunta", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(mCtx, e.message, Toast.LENGTH_SHORT).show()
        }
        db.close()
    }

    fun deleteClass(classID: Int, schoolID: Int): Boolean {
        val qry = "Delete From $CLASS_TABLE where $COLUMN_CLASSID = $classID and $COLUMN_SCHOOLID = schoolID"
        val db = this.writableDatabase
        var result: Boolean = false
        try {
            val cursor = db.execSQL(qry)
            result = true
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Errore nella cancellazione")
        }
        db.close()
        return result
    }

    fun updateClass(id: String, className: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        var result: Boolean
        contentValues.put(COLUMN_CLASSNAME, className)
        try {
            db.update(CLASS_TABLE, contentValues, "$COLUMN_CLASSID = ?", arrayOf(id))
            result = true
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Errore nell'aggiornamento")
            result = false
        }
        return result
    }
    fun getStudents(mCtx: Context): ArrayList<Student> {
        val qry = "Select * From $STUDENT_TABLE"
        val db: SQLiteDatabase = this.readableDatabase
        val cursor: Cursor = db.rawQuery(qry, null)
        val students = ArrayList<Student>()

        if (cursor.count == 0)
            Toast.makeText(mCtx, "Nessuno studente trovato", Toast.LENGTH_SHORT).show() else {
            cursor.moveToFirst()
            while (!cursor.isAfterLast()) {
                val student = Student()
                student.studentID = cursor.getInt(cursor.getColumnIndex(COLUMN_STUDENTID))
                student.studentName = cursor.getString(cursor.getColumnIndex(COLUMN_STUDENTNAME))
                student.studentSurname = cursor.getString(cursor.getColumnIndex(
                    COLUMN_STUDENTSURNAME))
                student.schoolID = cursor.getInt(cursor.getColumnIndex(COLUMN_SCHOOLID))
                student.classID = cursor.getInt(cursor.getColumnIndex(COLUMN_CLASSID))
                students.add(student)
                cursor.moveToNext()
            }
            Toast.makeText(mCtx, "${cursor.count.toString()} studenti trovati", Toast.LENGTH_SHORT).show()
        }
        cursor.close()
        db.close()
        return students
    }

    fun addStudent(mCtx: Context, student: Student) {
        val values = ContentValues()
        values.put(COLUMN_STUDENTNAME, student.studentName)
        values.put(COLUMN_STUDENTSURNAME, student.studentSurname)
        values.put(COLUMN_SCHOOLID, student.schoolID)
        values.put(COLUMN_CLASSID, student.classID)
        val db = this.writableDatabase
        try {
            db.insert(STUDENT_TABLE, null, values)
            Toast.makeText(mCtx, "Studente aggiunto", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(mCtx, e.message, Toast.LENGTH_SHORT).show()
        }
        db.close()
    }

    fun deleteStudent(studentID: Int, schoolID: Int, classID: Int): Boolean {
        val qry = "Delete From $STUDENT_TABLE where $COLUMN_STUDENTID = $studentID and $COLUMN_SCHOOLID = schoolID and $COLUMN_CLASSID = $classID"
        val db = this.writableDatabase
        var result: Boolean = false
        try {
            val cursor = db.execSQL(qry)
            result = true
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Errore nella cancellazione")
        }
        db.close()
        return result
    }

    fun updateStudent(id: String, studentName: String, studentSurname: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        var result: Boolean
        contentValues.put(COLUMN_STUDENTNAME, studentName)
        contentValues.put(COLUMN_STUDENTSURNAME, studentSurname)
        try {
            db.update(STUDENT_TABLE, contentValues, "$COLUMN_STUDENTID = ?", arrayOf(id))
            result = true
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Errore nell'aggiornamento")
            result = false
        }
        return result
    }

}