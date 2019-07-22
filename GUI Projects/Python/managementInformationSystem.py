# -*- coding: utf-8 -*-
"""
Created on Fri Jun 14 10:04:43 2019

@author: Lee Flame
"""

import wx 
import wx.xrc
import pyodbc
conn = pyodbc.connect("Driver={SQL Server Native Client 11.0};"
                      "Server=MELISSA;"
                      "Database=STU_DB;"
                      "Trusted_Connection=yes;")
################################################################################################################################################################################################
class MainWindow ( wx.Frame ):
	
	def __init__( self, parent):
		super().__init__ (parent, id = wx.ID_ANY, title = "Manament Information System - Main Page", pos = wx.DefaultPosition, size = wx.Size( 500,300 ), style = wx.DEFAULT_FRAME_STYLE|wx.TAB_TRAVERSAL )
		
		self.SetSizeHints( wx.DefaultSize, wx.DefaultSize )
		
		bSizer1 = wx.BoxSizer( wx.VERTICAL )
		
		self.appName = wx.StaticText( self, wx.ID_ANY, "Manament Information System", wx.DefaultPosition, wx.DefaultSize, wx.ALIGN_CENTRE )
		self.appName.Wrap( -1 )
		self.appName.SetFont( wx.Font( 20, 72, 93, 90, False, "Bodoni MT" ) )
		
		bSizer1.Add( self.appName, 0, wx.ALL|wx.EXPAND|wx.ALIGN_CENTER_HORIZONTAL, 5 )
		
		self.studentRegistry = wx.Button( self, wx.ID_ANY, "Student Registry", wx.DefaultPosition, wx.DefaultSize, 0 )
		self.studentRegistry.SetFont( wx.Font( 9, 72, 90, 92, False, "Bell MT" ) )
		self.studentRegistry.SetToolTip( "Select this to view the students registry" )
		
		bSizer1.Add( self.studentRegistry, 0, wx.ALIGN_CENTER_HORIZONTAL|wx.ALL, 5 )
		
		self.courseRegistry = wx.Button( self, wx.ID_ANY, "Courses", wx.DefaultPosition, wx.DefaultSize, 0 )
		self.courseRegistry.SetFont( wx.Font( 9, 72, 90, 92, False, "Bell MT" ) )
		self.courseRegistry.SetToolTip( "Select this to view the courses" )
		
		bSizer1.Add( self.courseRegistry, 0, wx.ALL|wx.ALIGN_CENTER_HORIZONTAL, 5 )
		
		self.major = wx.Button( self, wx.ID_ANY, "Majors", wx.DefaultPosition, wx.DefaultSize, 0 )
		self.major.SetFont( wx.Font( 9, 72, 90, 92, False, "Bell MT" ) )
		self.major.SetToolTip( u"Select this to view the majors" )
		
		bSizer1.Add( self.major, 0, wx.ALL|wx.ALIGN_CENTER_HORIZONTAL, 5 )
		
		self.score = wx.Button( self, wx.ID_ANY, "Scores", wx.DefaultPosition, wx.DefaultSize, 0 )
		self.score.SetFont( wx.Font( 9, 72, 90, 92, False, "Bell MT" ) )
		self.score.SetToolTip( "Select this to view the scores" )
		
		bSizer1.Add( self.score, 0, wx.ALL|wx.ALIGN_CENTER_HORIZONTAL, 5 )
		
		
		self.SetSizer( bSizer1 )
		self.Layout()
		
		self.Centre( wx.BOTH )
		
		# Connect Events
		self.studentRegistry.Bind( wx.EVT_BUTTON, self.onStudent )
		self.courseRegistry.Bind( wx.EVT_BUTTON, self.onCourse )
        
		self.major.Bind( wx.EVT_BUTTON, self.onMajor )
		self.score.Bind( wx.EVT_BUTTON, self.onScores )
        
	
	def __del__( self ):
		pass
	
	
	# Virtual event handlers, overide them in your derived class
	def onStudent( self, event ):
		frame = GeneralList()
		frame.Show()
	
	def onCourse( self, event ):
		frame = GeneralCourseList()
		frame.Show()
	
	def onMajor( self, event ):
		frame = GeneralMajorList()
		frame.Show()
	
	def onScores( self, event ):
		frame = GeneralScoreList()
		frame.Show()
	
#################################################################################################################################################################
	
class MyPanel(wx.Panel):
    def __init__(self,parent):
        super().__init__(parent)
        main_sizer = wx.BoxSizer(wx.VERTICAL)
        self.row_obj_dict = []
        self.query = ["SELECT * FROM student"]
        
        self.list_ctrl = wx.ListCtrl(self,size=(-1, 100),style=wx.LC_REPORT|wx.BORDER_SUNKEN)
        self.updateRecord()

        main_sizer.Add(self.list_ctrl,0, wx.ALL|wx.EXPAND,5)
        row_sizer = wx.BoxSizer(wx.HORIZONTAL)
        
        edit_button = wx.Button(self, label='Edit')
        edit_button.Bind(wx.EVT_BUTTON,self.onEdit)
        
        delete = wx.Button(self, label='Delete')
        delete.Bind(wx.EVT_BUTTON,self.onDelete)
        
        add = wx.Button(self, label='Add')
        add.Bind(wx.EVT_BUTTON,self.onAdd)
        
        row_sizer.Add(edit_button, 0, wx.ALL,5)
        row_sizer.Add(add, 0, wx.ALL,5)
        row_sizer.Add(delete, 0, wx.ALL,5)
        
        main_sizer.Add(row_sizer,0,wx.EXPAND)
        
        row_sizers = wx.BoxSizer(wx.HORIZONTAL)
        
        self.text_ctrl = wx.TextCtrl(self)
        
        seach = wx.Button(self, label="Search Name")
        seach.Bind(wx.EVT_BUTTON,self.onSearch)
        
        reload = wx.Button(self, label='Reload')
        reload.Bind(wx.EVT_BUTTON,self.onaHey)
        

        row_sizers.Add(self.text_ctrl,0,wx.ALL,5)
        row_sizers.Add(seach,0,wx.ALL,5)
        row_sizers.Add(reload,0,wx.ALL,5)
        
        main_sizer.Add(row_sizers,0,wx.EXPAND)
        self.SetSizer(main_sizer)
        
    def onaHey(self, event):
        self.query[0] = "SELECT * from student"
        self.updateRecord()
    
    def onSearch(self,event):
        self.query[0] = "SELECT * from student where NAME = \'" + self.text_ctrl.GetValue() + "\'"
        self.updateRecord()
    
    def onDelete(self, event):
        selection = self.list_ctrl.GetFocusedItem()
        if selection >= 0:
            it = self.row_obj_dict[selection]
            st = it[0]
            cursor = conn.cursor()
            cursor.execute('delete from student where NO = ' + st)
            conn.commit()
            cursor.close()
            self.updateRecord()
        
    def onAdd(self, event):
        dlg = AddDialog()
        dlg.ShowModal()
        
        self.updateRecord()
        dlg.Destroy()
        
    def onEdit(self,event):
        selection = self.list_ctrl.GetFocusedItem()
        if selection >= 0:
            item = self.row_obj_dict[selection]
            dlg = EditDialog(item)
            dlg.ShowModal()
            self.updateRecord()
            dlg.Destroy()
        
    def updateRecord(self):
        self.list_ctrl.ClearAll()
        
        self.list_ctrl.InsertColumn(0, 'NO', width=140)
        self.list_ctrl.InsertColumn(1, 'NAME', width=140)
        self.list_ctrl.InsertColumn(2, 'SEX', width=200)
        self.list_ctrl.InsertColumn(3,'BIRTHDAY', width=200)
        self.list_ctrl.InsertColumn(4,'MEMBER', width=200)
        self.list_ctrl.InsertColumn(5,'MAJOR KEY', width=200)
        self.list_ctrl.InsertColumn(6,'REMARK', width=200)
        index = 0
        cursor = conn.cursor()
        st = self.query[0]
        cursor.execute(st)
        del self.row_obj_dict[:]
        for row in cursor:
            self.list_ctrl.InsertItem(index,str(row[0]))
            self.list_ctrl.SetItem(index,1,str(row[1]))
            self.list_ctrl.SetItem(index,2,str(row[2]))
            self.list_ctrl.SetItem(index,3,str(row[3]))
            self.list_ctrl.SetItem(index,4,str(row[4]))
            self.list_ctrl.SetItem(index,5,str(row[5]))
            self.list_ctrl.SetItem(index,6,str(row[6]))
            self.row_obj_dict.append(row)
            index += 1
            
        
        
################################################################################################
class AddDialog(wx.Dialog):
    def __init__(self):
        title = 'Add new Student'
        super().__init__(parent=None,id = wx.ID_ANY,title=title,pos = wx.DefaultPosition,size = wx.Size( 590,415 ), style = wx.DEFAULT_DIALOG_STYLE)
        self.main_sizer = wx.BoxSizer(wx.VERTICAL)

        self.no = wx.TextCtrl(self)
        self.add_widgets('NO:',self.no)
        
        self.name = wx.TextCtrl(self)
        self.add_widgets('NAME:',self.name)
        
        self.sex = wx.TextCtrl(self)
        self.add_widgets('SEX:',self.sex)
        
        self.birthday = wx.TextCtrl(self)
        self.add_widgets('BIRTHDAY:',self.birthday)
        
        self.member = wx.TextCtrl(self)
        self.add_widgets('MEMBER:',self.member)
        
        self.majorKey = wx.TextCtrl(self)
        self.add_widgets('MAJOR_KEY:',self.majorKey)
        
        self.remark = wx.TextCtrl(self)
        self.add_widgets('REMARK:',self.remark)
        
        saved = wx.Button(self,label = 'Save')
        saved.Bind(wx.EVT_BUTTON,self.onSaveNew)
        self.main_sizer.Add(saved,0,wx.ALL,5)
        self.SetSizer(self.main_sizer)
        
    def add_widgets(self,label_text, text_ctrl):
        row_sizer = wx.BoxSizer(wx.HORIZONTAL)
        label = wx.StaticText(self, label=label_text,size=(50, -1))
        row_sizer.Add(label,0,wx.ALL,5)
        row_sizer.Add(text_ctrl,1,wx.ALL|wx.EXPAND,5)
        self.main_sizer.Add(row_sizer,0,wx.EXPAND)
    
    def onSaveNew(self,event):
        tuples = []
        tuples.append(self.no.GetValue())
        tuples.append(self.name.GetValue())
        tuples.append(self.sex.GetValue())
        tuples.append(self.birthday.GetValue())
        tuples.append(self.member.GetValue())
        tuples.append(self.majorKey.GetValue())
        tuples.append(self.remark.GetValue())
        if(self.remark.GetValue()== ""):
            tuples[6]= "None"
            
        st = "INSERT INTO student(NO, NAME, SEX, BIRTHDAY, MEMBER, MAJOR_KEY, REMARK) VALUES(\'"+tuples[0]+"\', \'"+tuples[1]+"\', \'"+tuples[2]+"\', \'"+tuples[3]+"\', \'"+tuples[4]+"\', \'"+tuples[5]+"\', \'"+tuples[6]+"\')"
        cursor = conn.cursor()
        cursor.execute(st)
        conn.commit()
        cursor.close()
        self.Close()


class GeneralList(wx.Frame):
    def __init__(self):
        super().__init__(parent=None,title='Students Registry')
        self.panel = MyPanel(self)
        self.Show()
        
class EditDialog(wx.Dialog):
    def __init__(self,item):
        title = f'Editing "{item[1]}"'
        super().__init__(parent=None,id = wx.ID_ANY,title=title,pos = wx.DefaultPosition,size = wx.Size( 590,415 ), style = wx.DEFAULT_DIALOG_STYLE)
        self.item = item
        self.main_sizer = wx.BoxSizer(wx.VERTICAL)
        
        self.no = wx.TextCtrl(self, value=self.item[0])
        self.add_widgets('NO:',self.no)
        
        self.name = wx.TextCtrl(self,value=self.item[1])
        self.add_widgets('NAME:',self.name)
        
        self.sex = wx.TextCtrl(self,value = self.item[2])
        self.add_widgets('SEX:',self.sex)
        
        self.birthday = wx.TextCtrl(self,value = str(self.item[3]))
        self.add_widgets('BIRTHDAY:',self.birthday)
        
        self.member = wx.TextCtrl(self,value = str(self.item[4]))
        self.add_widgets('MEMBER:',self.member)
        
        self.majorKey = wx.TextCtrl(self,value = str(self.item[5]))
        self.add_widgets('MAJOR_KEY:',self.majorKey)
        
        self.remark = wx.TextCtrl(self,value = str(self.item[6]))
        self.add_widgets('REMARK:',self.remark)
        
        saved = wx.Button(self,label = 'Save edit')
        saved.Bind(wx.EVT_BUTTON,self.onSaveUp)
        self.main_sizer.Add(saved,0,wx.ALL,5)
        self.SetSizer(self.main_sizer)
        
    def add_widgets(self,label_text, text_ctrl):
        row_sizer = wx.BoxSizer(wx.HORIZONTAL)
        label = wx.StaticText(self, label=label_text,size=(50, -1))
        row_sizer.Add(label,0,wx.ALL,5)
        row_sizer.Add(text_ctrl,1,wx.ALL|wx.EXPAND,5)
        self.main_sizer.Add(row_sizer,0,wx.EXPAND)
    
    def onSaveUp(self,event):
        tuples = []
        tuples.append(self.no.GetValue())
        tuples.append(self.name.GetValue())
        tuples.append(self.sex.GetValue())
        tuples.append(self.birthday.GetValue())
        tuples.append(self.member.GetValue())
        tuples.append(self.majorKey.GetValue())
        tuples.append(self.remark.GetValue())
        st = "Update student set NAME = \'" + tuples[1] + "\', SEX = \'" + tuples[2] + "\', BIRTHDAY = \'" + tuples[3] + "\', MEMBER = \'" + tuples[4] + "\', MAJOR_KEY = \'" + tuples[5] + "\', REMARK = \'" + tuples[6] + "\' where NO = \'" + tuples[0] + "\'"
        cursor = conn.cursor()
        cursor.execute(st)
        conn.commit()
        cursor.close()

        self.Close()
        
    

#################################################################################################################################


class MyMajorPanel(wx.Panel):
    def __init__(self,parent):
        super().__init__(parent)
        main_sizer = wx.BoxSizer(wx.VERTICAL)
        self.row_obj_dict = []
        self.query = ["SELECT * FROM major"]
        
        self.list_ctrl = wx.ListCtrl(self,size=(-1, 100),style=wx.LC_REPORT|wx.BORDER_SUNKEN)
        self.updateRecord()
        
       # self.list_ctrl.InsertColumn(0, 'MAJOR Key', width=140)
        #self.list_ctrl.InsertColumn(1, 'MAJOR', width=140)

        main_sizer.Add(self.list_ctrl,0, wx.ALL|wx.EXPAND,5)
        
        row_sizer = wx.BoxSizer(wx.HORIZONTAL)
        
        edit_button = wx.Button(self, label='Edit')
        edit_button.Bind(wx.EVT_BUTTON,self.onEdit)
        
        delete = wx.Button(self, label='Delete')
        delete.Bind(wx.EVT_BUTTON,self.onDelete)
        
        add = wx.Button(self, label='Add')
        add.Bind(wx.EVT_BUTTON,self.onAdd)
        
        row_sizer.Add(edit_button, 0, wx.ALL,5)
        row_sizer.Add(add, 0, wx.ALL,5)
        row_sizer.Add(delete, 0, wx.ALL,5)
        
        main_sizer.Add(row_sizer,0,wx.EXPAND)
        
        row_sizers = wx.BoxSizer(wx.HORIZONTAL)
        
        self.text_ctrl = wx.TextCtrl(self)
        
        seach = wx.Button(self, label="Search major")
        seach.Bind(wx.EVT_BUTTON,self.onSearch)
        
        
        reload = wx.Button(self, label="Reload")
        reload.Bind(wx.EVT_BUTTON,self.onaHey)
        
        row_sizers.Add(self.text_ctrl,0,wx.ALL,5)
        row_sizers.Add(seach,0,wx.ALL,5)
        row_sizers.Add(reload,0,wx.ALL,5)
        
        main_sizer.Add(row_sizers,0,wx.EXPAND)
        self.SetSizer(main_sizer)
        
    def onaHey(self, event):
        self.query[0] = "SELECT * from major"
        self.updateRecord()
        
    def onSearch(self,event):
        self.query[0] = "SELECT * from major where MAJOR = \'" + self.text_ctrl.GetValue() + "\'"
        self.updateRecord()
        
    def onDelete(self, event):
        selection = self.list_ctrl.GetFocusedItem()
        if selection >= 0:
            it = self.row_obj_dict[selection]
            st = it[0]
            cursor = conn.cursor()
            cursor.execute('delete from major where MAJOR_KEY = ' + st)
            conn.commit()
            cursor.close()
            self.updateRecord()
        
    def onAdd(self, event):
        dlg = AddMajorDialog()
        dlg.ShowModal()
        
        self.updateRecord()
        dlg.Destroy()
        
    def onEdit(self,event):
        selection = self.list_ctrl.GetFocusedItem()
        if selection >= 0:
            item = self.row_obj_dict[selection]
            dlg = EditMajor(item)
            dlg.ShowModal()
            self.updateRecord()
            dlg.Destroy()
        
    def updateRecord(self):
        self.list_ctrl.ClearAll()
        
        self.list_ctrl.InsertColumn(0, 'MAJOR Key', width=140)
        self.list_ctrl.InsertColumn(1, 'MAJOR', width=140)
        index = 0
        cursor = conn.cursor()
        cursor.execute(self.query[0])
        del self.row_obj_dict[:]
        for row in cursor:
            self.list_ctrl.InsertItem(index,str(row[0]))
            self.list_ctrl.SetItem(index,1,str(row[1]))
            self.row_obj_dict.append(row)
            index += 1

class AddMajorDialog(wx.Dialog):
    def __init__(self):
        title = 'Add new major'
        super().__init__(parent=None,id = wx.ID_ANY,title=title,pos = wx.DefaultPosition,size = wx.Size( 590,415 ), style = wx.DEFAULT_DIALOG_STYLE)
        self.main_sizer = wx.BoxSizer(wx.VERTICAL)

        self.majorkey = wx.TextCtrl(self)
        self.add_widgets('MAJOR_KEY:',self.majorkey)
        
        self.major = wx.TextCtrl(self)
        self.add_widgets('MAJOR:',self.major)
        
                
        saved = wx.Button(self,label = 'Save')
        saved.Bind(wx.EVT_BUTTON,self.onSaveNew)
        self.main_sizer.Add(saved,0,wx.ALL,5)
        self.SetSizer(self.main_sizer)
        
    def add_widgets(self,label_text, text_ctrl):
        row_sizer = wx.BoxSizer(wx.HORIZONTAL)
        label = wx.StaticText(self, label=label_text,size=(50, -1))
        row_sizer.Add(label,0,wx.ALL,5)
        row_sizer.Add(text_ctrl,1,wx.ALL|wx.EXPAND,5)
        self.main_sizer.Add(row_sizer,0,wx.EXPAND)
        
    def onSaveNew(self,event):
        tuples = []
        tuples.append(self.majorkey.GetValue())
        tuples.append(self.major.GetValue())
        
        if(tuples[0]!= "" or tuples[1] != ""):
            st = "INSERT INTO major(MAJOR_KEY, MAJOR) VALUES(\'"+tuples[0]+"\', \'"+tuples[1]+"\')"
            cursor = conn.cursor()
            cursor.execute(st)
            conn.commit()
            cursor.close()
            
        
        self.Close()
            
class GeneralMajorList(wx.Frame):
    def __init__(self):
        super().__init__(parent=None,title='Majors')
        self.panel = MyMajorPanel(self)
        self.Show()
        
class EditMajor(wx.Dialog):
    def __init__(self,item):
        title = f'Editing "{item[1]}"'
        super().__init__(parent=None,id = wx.ID_ANY,title=title,pos = wx.DefaultPosition,size = wx.Size( 590,415 ), style = wx.DEFAULT_DIALOG_STYLE)
        self.item = item
        self.main_sizer = wx.BoxSizer(wx.VERTICAL)
        
        self.majorKey = wx.TextCtrl(self, value=self.item[0])
        self.add_widgets('MAJOR KEY:',self.majorKey)
        
        self.major = wx.TextCtrl(self,value=self.item[1])
        self.add_widgets('MAJOR:',self.major)
        
                
        btn = wx.BoxSizer()
        saved = wx.Button(self,label = 'Save edit')
        saved.Bind(wx.EVT_BUTTON,self.onSaveUp)
        btn.Add(saved,0,wx.ALL,5)
        btn.Add(wx.Button(self,id=wx.ID_CANCEL),0,wx.ALL,5)
        self.main_sizer.Add(btn,0,wx.CENTER)
        self.SetSizer(self.main_sizer)
        
    def add_widgets(self,label_text, text_ctrl):
        row_sizer = wx.BoxSizer(wx.HORIZONTAL)
        label = wx.StaticText(self, label=label_text,size=(50, -1))
        row_sizer.Add(label,0,wx.ALL,5)
        row_sizer.Add(text_ctrl,1,wx.ALL|wx.EXPAND,5)
        self.main_sizer.Add(row_sizer,0,wx.EXPAND)
    
    def onSaveUp(self,event):
        tuples = []
        tuples.append(self.majorKey.GetValue())
        tuples.append(self.major.GetValue())

        
        st = "Update major set MAJOR = \'" + tuples[1] + "\' where MAJOR_KEY = \'" + tuples[0] + "\'"
        cursor = conn.cursor()
        cursor.execute(st)
        conn.commit()
        cursor.close()

        self.Close()
        
#################################################################################################################################
    
class MyCoursePanel(wx.Panel):
    def __init__(self,parent):
        super().__init__(parent)
        main_sizer = wx.BoxSizer(wx.VERTICAL)
        self.row_obj_dict = []
        
        self.list_ctrl = wx.ListCtrl(self,size=(-1, 100),style=wx.LC_REPORT|wx.BORDER_SUNKEN)
        self.query = ["SELECT * FROM Course"]
        self.updateRecord()

        main_sizer.Add(self.list_ctrl,0, wx.ALL|wx.EXPAND,5)
        
        row_sizer = wx.BoxSizer(wx.HORIZONTAL)
        
        edit_button = wx.Button(self, label='Edit')
        edit_button.Bind(wx.EVT_BUTTON,self.onEdit)
        
        delete = wx.Button(self, label='Delete')
        delete.Bind(wx.EVT_BUTTON,self.onDelete)
        
        add = wx.Button(self, label='Add')
        add.Bind(wx.EVT_BUTTON,self.onAdd)
        
        row_sizer.Add(edit_button, 0, wx.ALL,5)
        row_sizer.Add(add, 0, wx.ALL,5)
        row_sizer.Add(delete, 0, wx.ALL,5)
        
        main_sizer.Add(row_sizer,0,wx.EXPAND)
        row_sizers = wx.BoxSizer(wx.HORIZONTAL)
        
        self.text_ctrl = wx.TextCtrl(self)
        
        seach = wx.Button(self, label="Search Course")
        seach.Bind(wx.EVT_BUTTON,self.onSearch)
        reload = wx.Button(self, label="Reload")
        reload.Bind(wx.EVT_BUTTON,self.onaHey)
        
        row_sizers.Add(self.text_ctrl,0,wx.ALL,5)
        row_sizers.Add(seach,0,wx.ALL,5)
        row_sizers.Add(reload,0,wx.ALL,5)
        
        main_sizer.Add(row_sizers,0,wx.EXPAND)
        self.SetSizer(main_sizer)
        
    def onaHey(self, event):
        self.query[0] = "SELECT * from Course"
        self.updateRecord()
        
    def onSearch(self,event):
        self.query[0] = "SELECT * from Course where COURSE = \'" + self.text_ctrl.GetValue() + "\'"
        self.updateRecord()
        
    def onDelete(self, event):
        selection = self.list_ctrl.GetFocusedItem()
        if selection >= 0:
            it = self.row_obj_dict[selection]
            st = it[0]
            cursor = conn.cursor()
            cursor.execute('delete from course where COURSE_NO = \'' + st + "\'")
            cursor.commit()
            self.updateRecord()
        
    def onAdd(self, event):
        dlg = AddCourseDialog()
        dlg.ShowModal()
        
        self.updateRecord()
        dlg.Destroy()
        
    def onEdit(self,event):
        selection = self.list_ctrl.GetFocusedItem()
        if selection >= 0:
            item = self.row_obj_dict[selection]
            dlg = EditCourseDialog(item)
            dlg.ShowModal()
            self.updateRecord()
            dlg.Destroy()
        
    def updateRecord(self):
        self.list_ctrl.ClearAll()
        
        self.list_ctrl.InsertColumn(0, 'COURSE NO', width=140)
        self.list_ctrl.InsertColumn(1, 'COURSE', width=140)
        self.list_ctrl.InsertColumn(2, 'CREDIT', width=140)

        index = 0
        cursor = conn.cursor()
        st = self.query[0]
        cursor.execute(st)
        del self.row_obj_dict[:]
        for row in cursor:
            self.list_ctrl.InsertItem(index,str(row[0]))
            self.list_ctrl.SetItem(index,1,str(row[1]))
            self.list_ctrl.SetItem(index,2,str(row[2]))

           
            self.row_obj_dict.append(row)
            index += 1
        
class AddCourseDialog(wx.Dialog):
    def __init__(self):
        title = 'Add new course'
        super().__init__(parent=None,id = wx.ID_ANY,title=title,pos = wx.DefaultPosition,size = wx.Size( 590,415 ), style = wx.DEFAULT_DIALOG_STYLE)
        self.main_sizer = wx.BoxSizer(wx.VERTICAL)

        self.course_no = wx.TextCtrl(self)
        self.add_widgets('COURSE_NO:',self.course_no)
        
        self.course = wx.TextCtrl(self)
        self.add_widgets('COURSE:',self.course)
        
        self.credit = wx.TextCtrl(self)
        self.add_widgets('CREDIT:',self.credit)
        
        
        
        saved = wx.Button(self,label = 'Save')
        saved.Bind(wx.EVT_BUTTON,self.onSaveNew)
        self.main_sizer.Add(saved,0,wx.ALL,5)
        self.SetSizer(self.main_sizer)
        
    def add_widgets(self,label_text, text_ctrl):
        row_sizer = wx.BoxSizer(wx.HORIZONTAL)
        label = wx.StaticText(self, label=label_text,size=(50, -1))
        row_sizer.Add(label,0,wx.ALL,5)
        row_sizer.Add(text_ctrl,1,wx.ALL|wx.EXPAND,5)
        self.main_sizer.Add(row_sizer,0,wx.EXPAND)
    
    def onSaveNew(self,event):
        tuples = []
        tuples.append(self.course_no.GetValue())
        tuples.append(self.course.GetValue())
        tuples.append(str(self.credit.GetValue()))
        
        if(tuples[0] != "" or tuples[1] != "" or tuples[2] != ""):   
            st = "INSERT INTO Course(COURSE_NO, COURSE, CREDIT) VALUES(\'"+tuples[0]+"\', \'"+tuples[1]+"\', "+tuples[2]+")"
            cursor = conn.cursor()
            cursor.execute(st)
            conn.commit()
            cursor.close()
            self.Close()
        
class GeneralCourseList(wx.Frame):
    def __init__(self):
        super().__init__(parent=None,title='Courses')
        self.panel = MyCoursePanel(self)
        self.Show()
        
class EditCourseDialog(wx.Dialog):
    def __init__(self,item):
        title = f'Editing "{item[1]}"'
        super().__init__(parent=None,id = wx.ID_ANY,title=title,pos = wx.DefaultPosition,size = wx.Size( 590,415 ), style = wx.DEFAULT_DIALOG_STYLE)
        self.item = item
        self.main_sizer = wx.BoxSizer(wx.VERTICAL)
        
        self.courseNo = wx.TextCtrl(self, value=self.item[0])
        self.add_widgets('COURSE NO:',self.courseNo)
        
        self.course = wx.TextCtrl(self,value=self.item[1])
        self.add_widgets('COURSE',self.course)
        
        self.credit = wx.TextCtrl(self,value=str(self.item[2]))
        self.add_widgets('COURSE',self.credit)
        
                
        btn = wx.BoxSizer()
        saved = wx.Button(self,label = 'Save edit')
        saved.Bind(wx.EVT_BUTTON,self.onSaveUp)
        btn.Add(saved,0,wx.ALL,5)
        btn.Add(wx.Button(self,id=wx.ID_CANCEL),0,wx.ALL,5)
        self.main_sizer.Add(btn,0,wx.CENTER)
        self.SetSizer(self.main_sizer)
        
    def add_widgets(self,label_text, text_ctrl):
        row_sizer = wx.BoxSizer(wx.HORIZONTAL)
        label = wx.StaticText(self, label=label_text,size=(50, -1))
        row_sizer.Add(label,0,wx.ALL,5)
        row_sizer.Add(text_ctrl,1,wx.ALL|wx.EXPAND,5)
        self.main_sizer.Add(row_sizer,0,wx.EXPAND)
    
    def onSaveUp(self,event):
        tuples = []
        tuples.append(self.courseNo.GetValue())
        tuples.append(self.course.GetValue())
        tuples.append(self.credit.GetValue())
        
        st = "Update Course set COURSE = \'" + tuples[1] + "\', CREDIT = \'" + tuples[2] +"\' where COURSE_NO = \'" + tuples[0] + "\'"
        cursor = conn.cursor()
        cursor.execute(st)
        conn.commit()
        cursor.close()

        self.Close()
        
#################################################################################################################################
        
class MyScorePanel(wx.Panel):
    def __init__(self,parent):
        super().__init__(parent)
        main_sizer = wx.BoxSizer(wx.VERTICAL)
        self.row_obj_dict = []
        
        self.list_ctrl = wx.ListCtrl(self,size=(-1, 100),style=wx.LC_REPORT|wx.BORDER_SUNKEN)
        self.query = ["SELECT * FROM Score"]
        self.updateRecord()
        

        main_sizer.Add(self.list_ctrl,0, wx.ALL|wx.EXPAND,5)
        
        row_sizer = wx.BoxSizer(wx.HORIZONTAL)
        
        edit_button = wx.Button(self, label='Edit')
        edit_button.Bind(wx.EVT_BUTTON,self.onEdit)
        
        delete = wx.Button(self, label='Delete')
        delete.Bind(wx.EVT_BUTTON,self.onDelete)
        
        add = wx.Button(self, label='Add')
        add.Bind(wx.EVT_BUTTON,self.onAdd)
        
        row_sizer.Add(edit_button, 0, wx.ALL,5)
        row_sizer.Add(add, 0, wx.ALL,5)
        row_sizer.Add(delete, 0, wx.ALL,5)
        
        main_sizer.Add(row_sizer,0,wx.EXPAND)
        row_sizers = wx.BoxSizer(wx.HORIZONTAL)
        
        self.text_ctrl = wx.TextCtrl(self)
        
        seach = wx.Button(self, label="Search No.")
        seach.Bind(wx.EVT_BUTTON,self.onSearch)
        reload = wx.Button(self, label="Reload")
        reload.Bind(wx.EVT_BUTTON,self.onaHey)
        row_sizers.Add(self.text_ctrl,0,wx.ALL,5)
        row_sizers.Add(seach,0,wx.ALL,5)
        row_sizers.Add(reload,0,wx.ALL,5)
        
        main_sizer.Add(row_sizers,0,wx.EXPAND)
        self.SetSizer(main_sizer)
        
    def onaHey(self, event):
    	self.query[0] = "SELECT * from Score"
    	self.updateRecord()
        
    def onSearch(self,event):
        self.query[0] = "SELECT * from Score where NO = \'" + self.text_ctrl.GetValue() + "\'"
        self.updateRecord()
    
    def onDelete(self, event):
        selection = self.list_ctrl.GetFocusedItem()
        if selection >= 0:
            it = self.row_obj_dict[selection]
            st = it[0]
            cursor = conn.cursor()
            cursor.execute('delete from Score where NO = \'' +st + "\' and COURSE_NO = \'" + it[1] + '\'')
            cursor.commit()
            self.updateRecord()
        
    def onAdd(self, event):
        dlg = AddScoreDialog()
        dlg.ShowModal()
        
        self.updateRecord()
        dlg.Destroy()
        
    def onEdit(self,event):
        selection = self.list_ctrl.GetFocusedItem()
        if selection >= 0:
            item = self.row_obj_dict[selection]
            dlg = EditScoreDialog(item)
            dlg.ShowModal()
            self.updateRecord()
            dlg.Destroy()
        
    def updateRecord(self):
        self.list_ctrl.ClearAll()
        
        self.list_ctrl.InsertColumn(0, 'NO', width=140)
        self.list_ctrl.InsertColumn(1, 'COURSE NO', width=140)
        self.list_ctrl.InsertColumn(2, 'SCORE', width=140)
        
        index = 0
        cursor = conn.cursor()
        cursor.execute(self.query[0])
        del self.row_obj_dict[:]
        for row in cursor:
            self.list_ctrl.InsertItem(index,str(row[0]))
            self.list_ctrl.SetItem(index,1,str(row[1]))
            self.list_ctrl.SetItem(index,2,str(row[2]))
            
            self.row_obj_dict.append(row)
            index += 1
            

class AddScoreDialog(wx.Dialog):
    def __init__(self):
        title = 'Add new Score'
        super().__init__(parent=None,id = wx.ID_ANY,title=title,pos = wx.DefaultPosition,size = wx.Size( 590,415 ), style = wx.DEFAULT_DIALOG_STYLE)
        self.main_sizer = wx.BoxSizer(wx.VERTICAL)

        self.no = wx.TextCtrl(self)
        self.add_widgets('NO:',self.no)
        
        self.course_no = wx.TextCtrl(self)
        self.add_widgets('COURSE_NO:',self.course_no)
        
        self.score = wx.TextCtrl(self)
        self.add_widgets('SCORE:',self.score)
        
       
        
        saved = wx.Button(self,label = 'Save')
        saved.Bind(wx.EVT_BUTTON,self.onSaveNew)
        self.main_sizer.Add(saved,0,wx.ALL,5)
        self.SetSizer(self.main_sizer)
        
    def add_widgets(self,label_text, text_ctrl):
        row_sizer = wx.BoxSizer(wx.HORIZONTAL)
        label = wx.StaticText(self, label=label_text,size=(50, -1))
        row_sizer.Add(label,0,wx.ALL,5)
        row_sizer.Add(text_ctrl,1,wx.ALL|wx.EXPAND,5)
        self.main_sizer.Add(row_sizer,0,wx.EXPAND)
    
    def onSaveNew(self,event):
        tuples = []
        tuples.append(self.no.GetValue())
        tuples.append(self.course_no.GetValue())
        tuples.append(float(self.score.GetValue()))
        
        if( tuples[0] != "" or tuples[1] != "" or tuples[2] != ""):
            st = "INSERT INTO Score(NO, COURSE_NO, SCORE) VALUES(\'"+tuples[0]+"\', \'"+tuples[1]+"\', "+str(tuples[2])+")"
            cursor = conn.cursor()
            cursor.execute(st)
            conn.commit()
            cursor.close()
            self.Close()


        
        
            
class GeneralScoreList(wx.Frame):
    def __init__(self):
        super().__init__(parent=None,title='Scores')
        self.panel = MyScorePanel(self)
        self.Show()
        
class EditScoreDialog(wx.Dialog):
    def __init__(self,item):
        title = f'Editing "{item[1]}"'
        super().__init__(parent=None,title=title)
        self.item = item
        self.main_sizer = wx.BoxSizer(wx.VERTICAL)
        
        self.majorKey = wx.TextCtrl(self, value=self.item[0])
        self.add_widgets('NO:',self.no)
        
        self.major = wx.TextCtrl(self,value=self.item[1])
        self.add_widgets('COURSE NO:',self.name)
        
        self.major = wx.TextCtrl(self,value=self.item[1])
        self.add_widgets('SCORE:',self.name)
        
                
        btn = wx.BoxSizer()
        saved = wx.Button(self,label = 'Save edit')
        saved.Bind(wx.EVT_BUTTON,self.onSave)
        btn.Add(saved,0,wx.ALL,5)
        btn.Add(wx.Button(self,id=wx.ID_CANCEL),0,wx.ALL,5)
        self.main_sizer.Add(btn,0,wx.CENTER)
        self.SetSizer(self.main_sizer)
        
    def add_widgets(self,label_text, text_ctrl):
        row_sizer = wx.BoxSizer(wx.HORIZONTAL)
        label = wx.StaticText(self, label=label_text,size=(50, -1))
        row_sizer.Add(label,0,wx.ALL,5)
        row_sizer.Add(text_ctrl,1,wx.ALL|wx.EXPAND,5)
        self.main_sizer.Add(row_sizer,0,wx.EXPAND)
    
    def onSave(self,event):
        tuples = []
        tuples.append(self.no.GetValue())
        tuples.append(self.course_no.GetValue())
        tuples.append(self.score.GetValue())
        st = "Update Score set COURSE_NO = \'" + tuples[1] + "\', SCORE = " + str(tuples[2]) + "  where NO = \'" + tuples[0] + "\'"
        cursor = conn.cursor()
        cursor.execute(st)
        conn.commit()
        cursor.close()

        self.Close()
        
        

#################################################################################################################################
        

if __name__ == '__main__':
    app = wx.App(False)
    frame = MainWindow(None)
    frame.Show(True)
    app.MainLoop()
    