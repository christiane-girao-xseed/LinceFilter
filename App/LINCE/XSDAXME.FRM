VERSION 5.00
Object = "{3B7C8863-D78F-101B-B9B5-04021C009402}#1.1#0"; "RICHTX32.OCX"
Begin VB.Form XSDAXME 
   ClientHeight    =   5880
   ClientLeft      =   585
   ClientTop       =   915
   ClientWidth     =   8220
   LinkTopic       =   "Form1"
   PaletteMode     =   1  'UseZOrder
   ScaleHeight     =   24.5
   ScaleMode       =   4  'Character
   ScaleWidth      =   68.5
   Begin VB.PictureBox Picture1 
      Height          =   2736
      Left            =   105
      ScaleHeight     =   11.125
      ScaleMode       =   4  'Character
      ScaleWidth      =   66.75
      TabIndex        =   4
      Top             =   240
      Width           =   8070
      Begin VB.HScrollBar HScroll1 
         Height          =   285
         Left            =   0
         Max             =   17
         Min             =   1
         TabIndex        =   33
         TabStop         =   0   'False
         Top             =   2400
         Value           =   1
         Width           =   7770
      End
      Begin VB.VScrollBar VScroll1 
         Height          =   2460
         Left            =   7755
         Max             =   15
         TabIndex        =   30
         Top             =   0
         Value           =   1
         Width           =   255
      End
      Begin VB.PictureBox Picture2 
         BackColor       =   &H00C0C0C0&
         BorderStyle     =   0  'None
         Height          =   6000
         Left            =   15
         ScaleHeight     =   25
         ScaleMode       =   4  'Character
         ScaleWidth      =   80
         TabIndex        =   5
         TabStop         =   0   'False
         Top             =   0
         Width           =   9600
         Begin VB.Label LblDisp 
            BeginProperty Font 
               Name            =   "Courier"
               Size            =   9.75
               Charset         =   0
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   240
            Index           =   24
            Left            =   0
            TabIndex        =   34
            Top             =   0
            Width           =   9600
         End
         Begin VB.Label LblDisp 
            BeginProperty Font 
               Name            =   "Courier"
               Size            =   9.75
               Charset         =   0
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   240
            Index           =   0
            Left            =   30
            TabIndex        =   29
            Top             =   60
            Width           =   9600
         End
         Begin VB.Label LblDisp 
            BeginProperty Font 
               Name            =   "Courier"
               Size            =   9.75
               Charset         =   0
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   240
            Index           =   1
            Left            =   0
            TabIndex        =   28
            Top             =   0
            Width           =   9600
         End
         Begin VB.Label LblDisp 
            BeginProperty Font 
               Name            =   "Courier"
               Size            =   9.75
               Charset         =   0
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   240
            Index           =   2
            Left            =   0
            TabIndex        =   27
            Top             =   0
            Width           =   9600
         End
         Begin VB.Label LblDisp 
            BeginProperty Font 
               Name            =   "Courier"
               Size            =   9.75
               Charset         =   0
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   240
            Index           =   3
            Left            =   0
            TabIndex        =   26
            Top             =   0
            Width           =   9600
         End
         Begin VB.Label LblDisp 
            BeginProperty Font 
               Name            =   "Courier"
               Size            =   9.75
               Charset         =   0
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   240
            Index           =   4
            Left            =   0
            TabIndex        =   25
            Top             =   0
            Width           =   9600
         End
         Begin VB.Label LblDisp 
            BeginProperty Font 
               Name            =   "Courier"
               Size            =   9.75
               Charset         =   0
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   240
            Index           =   5
            Left            =   0
            TabIndex        =   24
            Top             =   0
            Width           =   9600
         End
         Begin VB.Label LblDisp 
            BeginProperty Font 
               Name            =   "Courier"
               Size            =   9.75
               Charset         =   0
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   240
            Index           =   6
            Left            =   0
            TabIndex        =   23
            Top             =   0
            Width           =   9600
         End
         Begin VB.Label LblDisp 
            BeginProperty Font 
               Name            =   "Courier"
               Size            =   9.75
               Charset         =   0
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   240
            Index           =   7
            Left            =   0
            TabIndex        =   22
            Top             =   0
            Width           =   9600
         End
         Begin VB.Label LblDisp 
            BeginProperty Font 
               Name            =   "Courier"
               Size            =   9.75
               Charset         =   0
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   240
            Index           =   8
            Left            =   0
            TabIndex        =   21
            Top             =   0
            Width           =   9600
         End
         Begin VB.Label LblDisp 
            BeginProperty Font 
               Name            =   "Courier"
               Size            =   9.75
               Charset         =   0
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   240
            Index           =   9
            Left            =   0
            TabIndex        =   20
            Top             =   0
            Width           =   9600
         End
         Begin VB.Label LblDisp 
            BeginProperty Font 
               Name            =   "Courier"
               Size            =   9.75
               Charset         =   0
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   240
            Index           =   10
            Left            =   0
            TabIndex        =   19
            Top             =   0
            Width           =   9600
         End
         Begin VB.Label LblDisp 
            BeginProperty Font 
               Name            =   "Courier"
               Size            =   9.75
               Charset         =   0
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   240
            Index           =   11
            Left            =   0
            TabIndex        =   18
            Top             =   0
            Width           =   9600
         End
         Begin VB.Label LblDisp 
            BeginProperty Font 
               Name            =   "Courier"
               Size            =   9.75
               Charset         =   0
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   240
            Index           =   12
            Left            =   0
            TabIndex        =   17
            Top             =   0
            Width           =   9600
         End
         Begin VB.Label LblDisp 
            BeginProperty Font 
               Name            =   "Courier"
               Size            =   9.75
               Charset         =   0
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   240
            Index           =   13
            Left            =   0
            TabIndex        =   16
            Top             =   0
            Width           =   9600
         End
         Begin VB.Label LblDisp 
            BeginProperty Font 
               Name            =   "Courier"
               Size            =   9.75
               Charset         =   0
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   240
            Index           =   14
            Left            =   0
            TabIndex        =   15
            Top             =   0
            Width           =   9600
         End
         Begin VB.Label LblDisp 
            BeginProperty Font 
               Name            =   "Courier"
               Size            =   9.75
               Charset         =   0
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   240
            Index           =   15
            Left            =   0
            TabIndex        =   14
            Top             =   0
            Width           =   9600
         End
         Begin VB.Label LblDisp 
            BeginProperty Font 
               Name            =   "Courier"
               Size            =   9.75
               Charset         =   0
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   240
            Index           =   16
            Left            =   0
            TabIndex        =   13
            Top             =   0
            Width           =   9600
         End
         Begin VB.Label LblDisp 
            BeginProperty Font 
               Name            =   "Courier"
               Size            =   9.75
               Charset         =   0
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   240
            Index           =   17
            Left            =   0
            TabIndex        =   12
            Top             =   0
            Width           =   9600
         End
         Begin VB.Label LblDisp 
            BeginProperty Font 
               Name            =   "Courier"
               Size            =   9.75
               Charset         =   0
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   240
            Index           =   18
            Left            =   0
            TabIndex        =   11
            Top             =   0
            Width           =   9600
         End
         Begin VB.Label LblDisp 
            BeginProperty Font 
               Name            =   "Courier"
               Size            =   9.75
               Charset         =   0
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   240
            Index           =   19
            Left            =   0
            TabIndex        =   10
            Top             =   0
            Width           =   9600
         End
         Begin VB.Label LblDisp 
            BeginProperty Font 
               Name            =   "Courier"
               Size            =   9.75
               Charset         =   0
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   240
            Index           =   20
            Left            =   0
            TabIndex        =   9
            Top             =   0
            Width           =   9600
         End
         Begin VB.Label LblDisp 
            BeginProperty Font 
               Name            =   "Courier"
               Size            =   9.75
               Charset         =   0
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   240
            Index           =   21
            Left            =   0
            TabIndex        =   8
            Top             =   0
            Width           =   9600
         End
         Begin VB.Label LblDisp 
            BeginProperty Font 
               Name            =   "Courier"
               Size            =   9.75
               Charset         =   0
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   240
            Index           =   22
            Left            =   0
            TabIndex        =   7
            Top             =   0
            Width           =   9600
         End
         Begin VB.Label LblDisp 
            BeginProperty Font 
               Name            =   "Courier"
               Size            =   9.75
               Charset         =   0
               Weight          =   400
               Underline       =   0   'False
               Italic          =   0   'False
               Strikethrough   =   0   'False
            EndProperty
            Height          =   240
            Index           =   23
            Left            =   0
            TabIndex        =   6
            Top             =   0
            Width           =   9600
         End
      End
   End
   Begin VB.CommandButton CmdCancel 
      Cancel          =   -1  'True
      Caption         =   "&Cancel"
      Height          =   285
      Left            =   7305
      TabIndex        =   3
      Top             =   5475
      Width           =   840
   End
   Begin VB.Timer Timer1 
      Left            =   7830
      Top             =   3180
   End
   Begin VB.TextBox TxtAx 
      Height          =   285
      Left            =   30
      TabIndex        =   0
      Top             =   5475
      Width           =   6225
   End
   Begin VB.CommandButton CmdOK 
      Caption         =   "&OK"
      Default         =   -1  'True
      Height          =   285
      Left            =   6390
      TabIndex        =   1
      Top             =   5475
      Width           =   855
   End
   Begin RichTextLib.RichTextBox TxtMe 
      Height          =   1950
      Left            =   90
      TabIndex        =   2
      TabStop         =   0   'False
      Top             =   3450
      Width           =   8085
      _ExtentX        =   14261
      _ExtentY        =   3440
      _Version        =   327680
      BackColor       =   12632256
      ReadOnly        =   -1  'True
      ScrollBars      =   3
      TextRTF         =   $"XSDAXME.frx":0000
   End
   Begin VB.Label Label2 
      AutoSize        =   -1  'True
      Caption         =   "Display"
      ForeColor       =   &H00FF0000&
      Height          =   195
      Left            =   120
      TabIndex        =   32
      Top             =   30
      Width           =   510
   End
   Begin VB.Label Label1 
      AutoSize        =   -1  'True
      BackColor       =   &H00C0C0C0&
      Caption         =   "Accept/Message"
      ForeColor       =   &H00FF0000&
      Height          =   195
      Left            =   135
      TabIndex        =   31
      Top             =   3195
      Width           =   1230
   End
   Begin VB.Line Line2 
      BorderColor     =   &H00808080&
      X1              =   0.75
      X2              =   68.25
      Y1              =   12.812
      Y2              =   12.812
   End
   Begin VB.Line Line1 
      BorderColor     =   &H00FFFFFF&
      X1              =   0.875
      X2              =   68.5
      Y1              =   12.875
      Y2              =   12.875
   End
End
Attribute VB_Name = "XSDAXME"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Dim TypeCommand As String
Dim aux As String
Dim i As Integer
Public Function ShowAccept() As String
   TypeCommand = "AX"
   TxtAx.Visible = True
   Me.Caption = Trim$(GLB_REPNAME) & " - Accept"
   CmdOK.Caption = "&OK"
   Me.Show 1
   ShowAccept = Trim$(aux)
End Function
Public Sub ShowMessage()
    Dim i As Integer
    TypeCommand = "ME"
    TxtAx.Visible = False
    Me.Caption = Trim$(GLB_REPNAME) & " - Accept"
    CmdOK.Caption = "&Close"
    TxtMe.Text = ""
    For i = 1 To GLB_MSGINDEX
        TxtMe.Text = TxtMe.Text & Trim$(GLB_MSGHEADER(i)) & vbTab & _
                     Trim$(GLB_MSGTRAILER(i)) & vbCrLf
    Next
End Sub
Public Sub ShowSMSG(Msg1 As String, Msg2 As String, DispLine As Single, DispCol As Single)
  Dim buffer As String
  Dim aux As String
  TypeCommand = "DI"
  aux = Msg1 + " " + Msg2
  If DispLine < 1 Then
     DispLine = 1
  End If
  If DispLine > 25 Then
     DispLine = 25
  End If
  buffer = LblDisp(DispLine - 1)
  LblDisp(DispLine - 1) = Mid$(buffer, 1, DispCol - 1) + aux + Mid$(buffer, Len(Mid$(buffer, 1, DispCol) + aux))
  Me.Caption = Trim$(GLB_REPNAME) & " - Display"
End Sub
Private Sub CmdCancel_Click()
  Unload Me
End Sub
Private Sub CmdOK_Click()
  If TypeCommand = "AX" Then
     GLB_MSGINDEX = GLB_MSGINDEX + 1
     If GLB_MSGINDEX > 20 Then GLB_MSGINDEX = 1
     GLB_MSGHEADER(GLB_MSGINDEX) = "Accept"
     GLB_MSGTRAILER(GLB_MSGINDEX) = Trim$(TxtAx)
  End If
  Unload Me
End Sub
Private Sub Form_Load()
   If TypeCommand = "ME" Or _
      TypeCommand = "DI" Then
      Me.Show
   End If
   'Display
    For i = 0 To 24
     LblDisp(i).Height = 1
     LblDisp(i).Width = 80
     LblDisp(i).Left = 0
     LblDisp(i).Top = i
     LblDisp(i).Caption = Space(80)
   Next

   Timer1.Interval = 0
   TxtMe.Text = ""
   For i = 1 To GLB_MSGINDEX
     TxtMe.Text = TxtMe.Text & Trim$(GLB_MSGHEADER(i)) & vbTab & _
             Trim$(GLB_MSGTRAILER(i)) & vbCrLf
   Next
   Timer1.Interval = 5
   
   
End Sub


Private Sub Form_Paint()
   If TypeCommand = "AX" Then
      TxtAx.SetFocus
   End If
End Sub

Private Sub Form_UnLoad(Cancel As Integer)
  If TypeCommand = "AX" Then
    aux = Trim$(TxtAx)
  End If
End Sub

Private Sub HScroll1_Change()
  Picture2.Left = 1 - HScroll1.Value
End Sub

Private Sub Timer1_Timer()
  Dim i As Integer
  i = Len(TxtMe.Text)
  TxtMe.SelStart = i
  Timer1.Interval = 0
End Sub

Private Sub VScroll1_Change()
'   Picture2.Top = 1 - VScroll1.Value
   Picture2.Top = -VScroll1.Value

End Sub
