package home;
import java.awt.*;
import java.sql.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.*;
import java.lang.*;

import javax.swing.UIManager.*;

import threeD.*;
public class Base extends JFrame implements ActionListener,ItemListener
{	
	
	//-------------DECLARATION OF VARIABLES FOR SIMULATOR----------------
	
	public ArrayList<VCoordinate3d>trajectory3DList=new ArrayList<VCoordinate3d>();
	//-------------------------------------------------------------------
	//--------------DECLARATION OF VARIABLES FOR GRAPH-------------------
	int intGraphArray1[]=new int[1000];
	boolean intGraphSig1=false;
	//-------------------------------------------------------------------
	
	//--------------DECLARATION OF ZOOM FACTOR VARIABLE-------------------
	float zoomFactor=1;
	//--------------------------------------------------------------------
	//--------------------SIGNAL HOUSE -----------------------------------
	SignalHouse signalHouse;	
	//--------------------------------------------------------------------
	
	//-----------------VARIABLES TO SHOW COMMUNICATIONS-------------------
	Location sendFrom=null;
	Location sendTo=null;
	
	//--------------------------------------------------------------------		
	int baseHeight;
	int baseWidth;
	
	//int baseStationCount=0;
	int sinkCount=0;
	//int nodeCount=0;
	int actuatorCount=0;
	int obstacleCount=0;
	
	boolean sourceNode=false;
	boolean sinkNode=false;
	boolean plotHexagonForNodes=false;
	boolean plotHexagonForActuators=false;
	boolean mmu_Node_Connection_Showable=true;
	boolean actuator_Tree_Connection_Showable=true;
	boolean baseStation_Tree_Connection_Showable=true;
	boolean interClusterConnectionShowable=false;
	
	boolean drawPathTree=false;
	
	boolean coordinateShowable=true;
	boolean pathTurningPointsShowable=true;
	
	int activeImplementation=1;

	
	//-----------DECLARING VARIABLES FOR CALCULATING RUNNING TIME--------
	long startTime;
	long endTime;
	
	long startTime1;
	long endTime1;
	
	//-------------------------------------------------------------------
	
	VarTestDialog varTestDialog;
	
	//--------------------DECLARING DEPLOYMENT SCHEMES--------------------
	String RANDOM="random";
	String FIXED="fixed";
	//--------------------------------------------------------------------
	//--------------------DECLARING VARIABLES FOR PATH TREE---------------
	
	byte FORWARD=1;
	byte BACKWORD=2;
	ArrayList<Partition>partition_List=new ArrayList<Partition>();
	
	//--------------------------------------------------------------------
	
	//----------DECLARING VARIABLES FOR PARTITION-------------------------
	
	int randomX=0;
	int randomY=0;
	
	//-------------------------------------------------------------------
	
	//-----------------DECLARING OBJECTIVE VARIABLE FOR ACTUATORS----------
	int CHARGING=1;
	int ROAMING=2;
	
	//---------------------------------------------------------------------
	//********DECLARING VARIABLES FOR GLIDER ASSISTED UNIFORM DEPLOYMENT******* 
	boolean gads=false;
	boolean grounded=false;
	boolean radiusReductionEnabled=true;
	boolean virtualPathUpdateEnabled=true;
	
	boolean windEnabled=false;
	
	float gliderRotationAnglePerStep=0.0f;
	float gliderTotalStepsForARotation=0.0f;
	float maxVirtualPathUpdateDist=0.0f;
	int pathUpdationCounter=0;
	float turningRadius;
	float tempTurningRadius;
	float totalError=0.0f;
	float maxDeploymentError;
	float glidingSpeedKmph;
	float glidingSpeedMps;
	float windSpeedMps;
	
	float gadsEffectiveDist;
	float gadsEffectiveRange;
	float gadsRadiusRange;
	Location furthestPointofCircle;
	Location oldMark=null; 
	Location newMark=null;
	
	Location oldCenter=null;
	Location newCenter=null;
	
	//int gadsProgressMeter=0;
	//boolean followPath=true;
	float gadsDr;
	float reducedTurningRadius;
	//float maxTurningRadius;
	int turnWithNmlRadius=0;
	int turnWithReducedRadius=0;
	//float 
	
	float windDxFloat;
	float windDyFloat;
	float windStepCounterFloatX;
	float windStepCounterFloatY;
	int windStepX;
	int windStepY;
	
	double simpleDist;
	double serpentineDisp;
	double hzSerpentineDisp;
	double angleOfGlide;
	
	GadsDeploymentControlDialog gadsDeploymentControlDialog;
	ArrayList<VCoordinate> nodeTrackList=new ArrayList<VCoordinate>();
	VCoordinate startPoint;
	public VLabel candidateNode;
	public Location candidateLoc;
	VLabel referenceBS;
	
	int LEFTTURN=0;
	int RIGHTTURN=1;
	
	JLabel northDirectionLb=new JLabel("NORTH");
	JLabel southDirectionLb=new JLabel("SOUTH");
	JLabel westDirectionLb=new JLabel("WEST");
	JLabel eastDirectionLb=new JLabel("EAST");
	
	JLabel northArrowLb=new JLabel(new ImageIcon("images/northArrow.png"));
	JLabel southArrowLb=new JLabel(new ImageIcon("images/southArrow.png"));
	JLabel eastArrowLb=new JLabel(new ImageIcon("images/eastArrow.png"));
	JLabel westArrowLb=new JLabel(new ImageIcon("images/westArrow.png"));
	
	//-------------VARIABLES FOR WIND----------------------------------------
	
	int windX=0;
	int windY=0; 
	
	int EAST=1;
	int WEST=2;
	int NORTH=3;
	int SOUTH=4;
	
	int windFrom=1;
	int windSpeed=1;
	
	//--------------VARIABLES FOR CONTROL-------------------------------------
	
	ArrayList <Location> checkPointList=new ArrayList<Location>();
	AreaOfInterest areaOfInterest=new AreaOfInterest();
	float currentSlope;
	float desiredSlope;
	Location turningPoint;
	ArrayList<Location> testPoints=new ArrayList<Location>();
	VSlope pathSlope=new VSlope();
	float pathWidth=20.0f;
	int turnCounter=1;
	boolean inRegion=false;
	
	//................
	
	float px1=100,py1=100;
	int testCounter=0;
	boolean pathUpdater=true;
	boolean pathFreezed=false;
	
	float oldDist=0;
	float newDist=0;
	
	int turningStep=1;
	
	//int trackLostCounter=0;
	

	//----------------ArrayList for Figures-----------------------------------
	double deployerTotalMovement=0.0;
	double deployerDroppingMovement=0.0;
	boolean drawForegroundFig=true;
	boolean drawBackgroundFig=true;
	//boolean showCrossbar=true;
	boolean showGrid=true;
	boolean droppingFlag=true;
	ArrayList<VFigure>foregroundFigureList=new ArrayList<VFigure>();
	ArrayList<VFigure>backgroundFigureList=new ArrayList<VFigure>();
	VFigEditorDialog figEditorDialog;
	VPathEditorDialog pathEditorDialog;
	//------------------------------------------------------------------------
	
	//-------------ARRAYLIST FOR PATH-----------------------------------------
	ArrayList<Location>deploymentLocList=new ArrayList<Location>();
	ArrayList<Location>pathList=new ArrayList<Location>();
	//------------------------------------------------------------------------
	
	//------------ARRAYLIST FOR SCATTERING CANNON-----------------------------
	ArrayList<ScatteringRange>scatteringRangeList=new ArrayList<ScatteringRange>();
	//------------------------------------------------------------------------
	
	VScriptEditor scriptEditor;
	VProgCommentEditor progCommentEditor;
	//................VARIABLE FOR 2-D DISPLAY--------------------------------
	
	//---------------VARIABLES FOR CENTRIFUGAL DEPLOYER-----------------------
	float scale=1;
	float deploymentError=1;
	double droppingHeight=500;
	float optimalNodeCount=100;
	double droppingRate=10f;
	float nodeMass=0.3f;
	float rpm=20;
	boolean conventionalDeployerFlag=true;
	float deploymentHelicopterSpeed=10.0f;

	//-----------------------------------------------------------------------
	
	JButton twoDBt;
	  
	//--------------VARIABLES OF GLIDER---------------------------------------
	public Location gliderNoseLoc;
	public Location gliderCenterLoc;
	public Location gliderTail1Loc;
	public Location gliderTail2Loc;
	public Location gliderAdvLoc;
	
	//Location gliderStartLoc;
	//Location gliderCurrentLoc;
	
	  VSlope glideSlope=new VSlope();
	
	float glidingStepDelay=0;
	float windDelay=100000;
	
	int s0=15;
	int s1=35;
	int s2=55;
	
	float xSlope=1;
	float ySlope=0;
	
	//------------------------------------------------------------------------
	
	VCoordinate turningCenter=null;
	Location clipBoardLoc=null;
	int x;
	int y;
	
	public double currentHeight;
	public float droppingSpeed;				//speed in meters per seconds
	
	int RIGHTCLICK=1;
	int LEFTCLICK=2;
	int currentClick=0;
	int xInr=1;
	int yInr=0;
	int clickCounter=0;
	int moveArray[]={1,0,-1,0};
	int leftArray[]={};
	
	//-------------VARIABLES FOR TRACK------------------------------------
	public ArrayList <ThreeDCoordinate> masterTrackList=new ArrayList<ThreeDCoordinate>();
	
	//*********************************************************************
	
	//-------------DECLARING VARIABLES FOR EDS-----------------------------
	
	ArrayList<ArrayList<Location>> daSubdivisionList=new ArrayList<ArrayList<Location>>();
	//ArrayList<ArrayList<VLabel>>clusterList=new ArrayList<ArrayList<VLabel>>();
	ArrayList<Cluster>clusterList=new ArrayList<Cluster>();
	ArrayList<VLabel>clusterHeadList=new ArrayList<VLabel>();
	ArrayList<Node_Rounds>nodeRoundList=new ArrayList<Node_Rounds>();
	
	
	int round=0;
	
	Cluster zeroCluster=new Cluster();
	Location locationClipBoard;
	VLabel baseStationClipBoard;
	VLabel tempNode;
	double energyForTransfer=50*0.000000001;//0.000000000001;
	double energyForReceiving=50*0.000000001;
	double efs=10*0.000000000001;
	double emp=0.0013*0.000000000001;
	double eda=5*0.000000000001;
	double vdo=Math.sqrt(efs/emp);
	
	//longDistEnergy e=E-(energyForTransfer+eda)*(4000)+emp*4000*(distance*distance*distance*distace);
	//shortDistEnergy e=E-(energyForTransfer+eda)*(4000)+emp*4000*(distance*distance);
	//---------------------------------------------------------------------
	
	//----------------DECLARING VARIABLES FOR NADS-----------------------
	
	long timeStamp=0;
	
	DHDS2 dhds2;
	int effectiveRadius=0;
	int placedNodesCount=0;
	ArrayList<VLabel>placedNodeList=new ArrayList<VLabel>();
	ArrayList<ArrayList<Location>>concurrencyList=new ArrayList<ArrayList<Location>>();
	ArrayList<Location>remainingLocList=new ArrayList<Location>();
	ArrayList<VLabel>unplacedNodeList=new ArrayList<VLabel>();
	ArrayList<Long>masterTimeLineList=new ArrayList<Long>();
	float maxDist=0;
	float diagonal=0;
	
	float totalMovementNadsPh1=0;
	float totalMovementNadsPh2_1=0;
	float totalMovementNadsPh2_2=0;
	float totalMovementNadsPh2_3=0;
	float totalMovementNadsPh2_4=0;
	float totalMovementNadsPh2_5=0;
	float totalMovementNadsPh2_6=0;
	float totalMovementNadsPh2_7=0;	
	float totalMovementNadsPh2=0;
	float totalMovementNadsPh3=0;
	
	int nodesPlacedNadsPh1=0;
	int nodesPlacedNadsPh2_1=0;
	int nodesPlacedNadsPh2_2=0;
	int nodesPlacedNadsPh2_3=0;
	int nodesPlacedNadsPh2_4=0;
	int nodesPlacedNadsPh2_5=0;
	int nodesPlacedNadsPh2_6=0;
	int nodesPlacedNadsPh2_7=0;
	int nodesPlacedNadsPh2=0;
	int nodesPlacedNadsPh3=0;
	
	float avgMovementNadsPh1=0;
	float avgMovementNadsPh2_1=0;
	float avgMovementNadsPh2_2=0;
	float avgMovementNadsPh2_3=0;
	float avgMovementNadsPh2_4=0;
	float avgMovementNadsPh2_5=0;
	float avgMovementNadsPh2_6=0;
	float avgMovementNadsPh2_7=0;
	float avgMovementNadsPh2=0;
	float avgMovementNadsPh3=0;
	//---------------------------------------------------------------------
	
	//*************DECLARING VARIABLES FOR UNIVERSAL ACCESS*****************
	int moveDelay=100;  //VARIABLE FOR CONTROLLING THE DELAY IN MOVEMENT STEPS
	
	//----------------------------------------------------------------------
	
	//----------------------------------------------------------------------
	int RANGE0=0;// DESIGNATES NO RANGE
	int RANGE1=1;// DESIGNATES NODE IN RANGE 1
	int RANGE2=2;// DESIGNATES NODE IN RANGE 2
	
	//****************DECLARING FLAGS FOR VIEW OF OBJECTS********************
	boolean commRangeShowable=false;
	boolean sensingRangeShowable=false;
	boolean connectionShowable=true;
	boolean coverageShowable=false;
	boolean desiredLocShowable=true;
	boolean rangeShowable=false;
	boolean floorLinesShowable=true;
	boolean bsConnShowable=true;
	boolean clusterConnShowable=false;
	boolean colorizeCR=false;
	
	//------------------------------------------------------------------------
	boolean actuatorCommRangeShowable=false;
	
	boolean shouldGenerateEvents=false;
	boolean markObstacle=true;
	//-----------------------------------------------------------------------
	boolean gridShowable=true;
	boolean nodesShowable=true;
	boolean virtualPathShowable=true;
	
	//-----------------------------------------------------------------------
	
	
	//*****************INITIALIZING CONSTANTS FOR ALGORITHMS*****************
	final int BROADCAST=-1;
	final int FLOODING=1;
	final int DIRECTIONAL_FLOODING=2;
	final int DIR_DIFFUSION=3;
	final int LEACH=4;
	final int XYZ=5;
	final int GOSSIPING=6;
	final int ANGULAR_FLOODING=7;
	final int CONTROLLED_FLOODING=8;
	
	//-----------------------------------------------------------------------
	
	//**************INITIALIZING CONSTANTS FOR NODE TYPES********************
	final int SENSORNODE=1;
	final int SINKNODE=2;
	final int BASESTATION=3;
	final int MMU=4;
	
	//----------------------------------------------------------------------
	
	//****************INITIALIZING CONSTANTS FOR PACKET TYPE****************
	//----------FOR DHDS-2-------------------------------------------------
	final int UNPLACED_NODE_INFORMER=1;
	final int UNALLOCATED_LOC_INFORMER=2;
	//---------------------------------------------------------------------
	//---------------------------------------------------------------------
	
	//**********INITIALIZING CONSTANTS FOR QUERY TYPES***********************
	final int REPLY=0;
	final int SET_BASE_INFO=1;
	final int SET_CRITICAL_TEMP=2;
	final int SET_CRITICAL_HUM=3;
	
	final int GET_TEMP=4;
	final int GET_MAX_TEMP=5;
	final int GET_MIN_TEMP=6;
	
	final int SET_GRADIENT=7;
	final int ADD_ME_AS_NEIGHBOR=8;
	
	final int MOVE=9;
	final int SELECTED_MOVE=10;     // MOVING THE SELECTED NODES TOWARDS THE BASE STATION
	final int DUALRANGE_SELECTED_MOVE=11; //MOVING THE SELECTED NODES TOWARDS THE BASE STATION USING DUAL RANGE MOVEMENT PATTERN
	
	final int GET_DISCONNECTED_NEIGHBORS=12;
	
	final int CRITICAL_ENERGY_LEVEL=13; //MESSAGE TO NODE REGARDING DEPLETING ENERGY LEVEL
	
	final int INSPECTOR=14; // SPECIFIES THAT THE PACKET IS AN INSPECTOR PACKET.
	
	final int GOSSIP=15;
	final int SET_GROUP=19;
	final int SET_DESIRED_LOC=20;
	final int STOP_MOVING=21;
	final int DHDS2_PHASE2=22;
	final int NADS_PHASE2=28;
	final int REPLY_NADS_PHASE2=29;
	final int RELOCATE_NADS_PHASE2=30;
//	final int SET_GRADIENT_NADS=31; // USED BY NADS PHASE3
	final int CONTROLLED_FLOODING_UDL_NADS=31; // USED BY NADS PHASE3
	final int CONTROLLED_FLOODING_USN_NADS=32; // USED BY NADS PHASE3
	//final int REPLY_DD_NADS_PHASE3=32; // USED BY NADS PHASE3
	//final int MOVE_THROUGH_RESERVED_NADS_PHASE3=33; //USED BY NADS PHASE-3
	
	
	
	int global_counter=0;
	
	
	final int SET_CLUSTER=23;
	final int SET_DATA=24;
	final int GET_DATA=25;			      //USED BY BASE STATION TO GET AGGRIGATED DATA FROM CLUSTER HEADS
	final int SEND_AGGRIGATED_DATA=26;    //USED BY CLUSTER HEADS TO SEND THE AGGRIGATED DATA TO BASE STATION
	final int SEND_AGGREGATED_DATA_MULTIHOP=27; //USED BY CLUSTER HEADS TO SEND AGGREGATED DATA TO BASE STATION USING MULTIHOP COMMUNICATION
	
	//-----------------------------------------------------------------------
	//****************INITIALIZING MISC CONSTANTS****************************
	final VLabel BROADCAST_DEST=new VLabel(0,"",null);
	//----------------------------------------------------------------------
	
	//*****************DECLAIRING DEPLOYMENT AREA PARAMETERS*****************
	int numberOfNodes=0;
	int powerPerNode=0;
	float commRangePerNode=1;
	float tempCommRangePerNode=0;
	float sensingRangePerNode=1;
	
	String deploymentScheme="";
	
	int numberOfActuators=0;
	float commRangePerActuator=1;
	float sensingRangePerActuator=1;
	String actuatorDeploymentScheme="";
	
	float totalMovement=0;
	float avgMovement=0;
	int percentageCoverage=0;
	float averageConnectivity=0.0f;
	
	//----------------------------------------------------------------------
	float totalActuatorMovement=0;
	float avgActuatorMovement=0;
	ArrayList<Float>actuatorMovementList=new ArrayList<Float>();
	//----------------------------------------------------------------------
	
	int range;
	//***********************************************************************
	
	
	//*******************INITIALIZING COLOURED IMAGE ICONS********************
	ImageIcon GREEN_ICON=new ImageIcon("images/green.png");
	ImageIcon LIME_ICON=new ImageIcon("images/lime.png");
	ImageIcon ORANGE_ICON=new ImageIcon("images/orange.png");
	ImageIcon BLUE_ICON=new ImageIcon("images/blue.png");
	ImageIcon DARKBLUE_ICON=new ImageIcon("images/blued.png");
	ImageIcon BROWN_ICON=new ImageIcon("images/brown.png");
	ImageIcon YELLOW_ICON=new ImageIcon("images/yellow.png");
	ImageIcon VOILET_ICON=new ImageIcon("images/voilet.png");
	ImageIcon BLACK_ICON=new ImageIcon("images/black.png");
	ImageIcon BLACK_ANIM_ICON=new ImageIcon("images/bs.gif");
	
	//************************************************************************
	
	//********************INITILIZING COLORS**********************************
	Color DARK_ORANGE=new Color(254,83,1);
	Color DARK_PINK=new Color(254,48,192);
	//************************************************************************
	
	
	//**************INITIALIZING COLORS FOR VPANELS**************************
	Color RED=Color.RED;
	Color BLUE=Color.BLUE;
	Color YELLOW=new Color(247,235,9);
	Color ORANGE=Color.ORANGE;
	Color GREY=Color.GRAY;
	Color GREEN=new Color(52,207,56);
	Color DARK_GREEN=new Color(0,64,0);
	Color LIGHT_GRAY=Color.LIGHT_GRAY;
	Color BLACK=Color.BLACK;
	Color BROWN=new Color(136,0,21);
	Color VOILET=new Color(163,73,164);
	Color LGHT_PINK=new Color(255,193,224);
	Color PINK=new Color(249,57,244);
	Color OFF_WHITE=new Color(235,235,235);
	//***********************************************************************
	
	//*********DECLAIRATION OF VARIABLES RELATED TO EVENTS********************
	int radius;
	int epicenterX=0;
	int epicenterY=0;
	boolean eventFlag=false;
	
	int partitionCounterX=0;
	int partitionCounterY=0;
	int partitionWidth=0;
	
	ArrayList<Integer> xEventOuterList=new ArrayList<Integer>();
	ArrayList<Integer> yEventOuterList=new ArrayList<Integer>();
	
	ArrayList<Integer> xEventMiddleList=new ArrayList<Integer>();
	ArrayList<Integer> yEventMiddleList=new ArrayList<Integer>();
	
	ArrayList<Integer> xEventInnerList=new ArrayList<Integer>();
	ArrayList<Integer> yEventInnerList=new ArrayList<Integer>();
	

	ArrayList<VLabel>spareList=new ArrayList<VLabel>();
	ArrayList<Range>rangeListForNodes=new ArrayList<Range>();
	ArrayList<Range>rangeListForActuators=new ArrayList<Range>();
	
	ArrayList<Location> desiredLocList=new ArrayList<Location>();   // LIST OF DESIRED POINTS IN THE DEPLOYMENT AREA
	ArrayList<Location>actuatorDesiredLocList=new ArrayList<Location>();
	ArrayList<Location> availDesiredLocListForNodes=new ArrayList<Location>();//LIST OF AVAILABLE DESIRED POINTS IN DEPLOYMENT AREA.
	ArrayList<Location> availDesiredLocListForActuators=new ArrayList<Location>();//LIST OF AVAILABLE DESIRED POINTS IN DEPLOYMENT AREA.
	
	ArrayList<Location> tempList=new ArrayList<Location>();
	ArrayList<Location> tempList2=new ArrayList<Location>();
	
	
	ArrayList<ConnectionPair>mstConnectionPairList=new ArrayList<ConnectionPair>();
	
	//***********************************************************************
	
	//*********DECLAIRATION OF MISC OBJECT REFERENCES************************
	RunConfig runConfig;
	BasePropertiesDialog baseProperties;
	VBaseOptionDialog vBaseOptionDialog;
	VNodeOptionDialog vNodeOptionDialog;
	VDaOptionDialog vDaOptionDialog;
	BaseStationControlRoom baseStationControlRoom;
	ObstacleDialog obstacleDialog;
	Alert alert;
	//VBaseOptionDialog optionWindow
	
	float deploymentAreaDivisionRange=70;  //RANGE IN WHICH THE DEPLOYMENT AREA WILL BE DIVIDED--> INITIALIZED IN generateHexagons() FUNCTION
	//int deploymentAreaParts=4;
	
	int powerScale1=400;
	int powerScale2=400;
	int powerScale3=400;
	
	int nodeSize=6;
	//-----------------------------------------------------------------------
	
	Graphics baseGraphics;
	
	//VLabel baseStationArray[];
	public ArrayList<VLabel>masterBSList=new ArrayList<VLabel>();
	VLabel sinkArray[];
	//VLabel nodeArray[];
	public ArrayList<VLabel>masterNodeList=new ArrayList<VLabel>();
	public ArrayList<Actuator>actuatorList=new ArrayList<Actuator>();
	//Actuator actuatorArray[];
	Obstacle obstacleArray[];
	Obstacle candidateObstacle;

	VLabel whoIsSource=null;
	VLabel whoIsSink=null;
	int xConnectorArray1[]=new int[1000];
	int yConnectorArray1[]=new int[1000];
	
	int xConnectorArray2[]=new int[1000];
	int yConnectorArray2[]=new int[1000];
	
	
	JToolBar simulatorTb;
	JButton newBt,clearBt,runBt,stopBt,graphBt,printBt,baseControlRoomBt,viewBt,eventInfoBt,storeBt,NADSResultsBt,deploymentMonitorBt,varTestBt;
	JComboBox implementationCb,nodeSizeCb,rangeCb,dAShapeCb;
	JButton nodeBt,bsBt,sinkBt,mobileSinkBt,mobileBsBt;
	JLabel dAShapeLb,radiusLb, dimensionLb;
	JTextField dxTf,dyTf,dRadiusTf;
	int xMin=100;
	int yMin=100;
	int dxCenter=350;
	int dyCenter=350;
	
	JLabel scaleLb=new JLabel("scale");
	
	JTextField scaleTf=new JTextField("");
	
	JLabel mousePositionLb, mxLb,myLb;
	JLabel eventPositionLb, exLb,eyLb;
	Base base;
	JPanel southPanel,upperSouthPanel,lowerSouthPanel;
	WorkAreaPanel centerPanel;

	
	//ToolKitPanel westPanel;
	
	BasicToolKitDialog toolKitDialog;
	
	GraphPanel graphPanel;
	GraphPanel graphPanel2;
	JButton graphPanelBt[]=new JButton[20];
	Joint joint;
	 JMenuBar baseMb;
	 JMenu fileMenu,customizeMenu,toolKitMenu,threeDMenu,windowMenu,graphMenu;
	 JMenuItem scriptEditorMi,packetEditorMi,figEditorMi,pathEditorMi,progCommentEditorMi,openMi,customizeRunMi,threeDRunMi;
	 JMenuItem basicToolkitMi; 
	 JMenuItem showGraphDataMi;
	 
	
	
	//-----------------------declaring private variables------------------------
	
	private int x1,y1,x2,y2;
	private VToolBox vToolBox;
	
	String implementationAr[]={"flooding","directional flooding","directed diffusion","--------"};
	String nodeSizeAr[]={"change node size","0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
	
	public Base()
	{
		super("QUORUM-COMM");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("images/titlelogo.png"));
		base=this;	
		signalHouse=new SignalHouse();
		joint=new Joint(base);
		vToolBox=new VToolBox(base);
		runConfig=new RunConfig(base);
		baseProperties=new BasePropertiesDialog(base);
		vBaseOptionDialog=new VBaseOptionDialog(base,null);
		vNodeOptionDialog=new VNodeOptionDialog(base,null);
		vDaOptionDialog=new VDaOptionDialog(base);
		graphPanel=new  GraphPanel(base);
		graphPanel2=new GraphPanel(base);
		alert=new Alert();
		dhds2=new DHDS2(base);
		
		

/*		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}*/
		
		//*************INITIALIZING VARIABLES FOR FIGURE EDITOR DIALOG******************
		figEditorDialog=new VFigEditorDialog(base);
		//******************************************************************************
		
		//*************INITIALIZING VARIABLES FOR SCRIPT EDITOR DIALOG******************
		scriptEditor=new VScriptEditor(base);
		//******************************************************************************
		
		//*************INITIALIZING VARIABLES FOR SCRIPT EDITOR DIALOG******************
		progCommentEditor=new VProgCommentEditor(base);
		//******************************************************************************
		
		//***********INITIALIZING VARIABLE DIALOG BOX***********************************
		varTestDialog=new VarTestDialog(base);
		
		//***********INITIALIZING VARIABLES FOR PATH EDITOR DIALOG**********************
		
		pathEditorDialog=new VPathEditorDialog(base);
		
		//******************************************************************************
		
		//**************INITIALIZING VARIABLES OF AIR ASSISTED DEPLOYMENT SCHEME*********
		//try
		//{
			//gadsDeploymentControlDialog=new GadsDeploymentControlDialog(base);
		//}catch(Exception e){}
		startPoint=new VCoordinate(200,200,1,base.BLUE,base);
		gliderNoseLoc=new Location(0,0,base);
		gliderCenterLoc=new Location(200,200,base);
		gliderTail1Loc=new Location(0,0,base);
		gliderTail2Loc=new Location(0,0,base);		
		
		base.gliderAdvLoc=new Location(0,0,base);
		
		//***************************************************************************
		
		activeImplementation=1;
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		this.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){System.exit(0);}});
		
		Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(dimension.width,dimension.height-15);
		//this.setResizable(false);
		//####################STARTING THE NORTH PANEL###################
		JPanel northPanel=new JPanel();
		//northPanel.setLayout(new GridLayout(2,1));

		
		northPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		simulatorTb=new JToolBar();
		
		
		newBt=new JButton("new");
		newBt.setBackground(new Color(255,255,255));
		newBt.addActionListener(this);
		
		clearBt=new JButton("clear");
		clearBt.setBackground(new Color(255,255,255));
		clearBt.addActionListener(this);
		
		runBt=new JButton("run");
		runBt.setBackground(new Color(255,255,255));
		runBt.addActionListener(this);
		
		stopBt=new JButton("stop");
		stopBt.setBackground(new Color(255,255,255));
		stopBt.addActionListener(this);
		
		graphBt=new JButton("graph");
		graphBt.setBackground(new Color(255,255,255));
		graphBt.addActionListener(this);
		
		printBt=new JButton("print");
		printBt.setBackground(new Color(255,255,255));
		printBt.addActionListener(this);
		
		baseControlRoomBt=new JButton("base station control room");
		baseControlRoomBt.setBackground(new Color(255,255,255));
		baseControlRoomBt.addActionListener(this);
		
		viewBt=new JButton("view");
		viewBt.setBackground(new Color(255,255,255));
		viewBt.addActionListener(this);
		
		eventInfoBt=new JButton("event info");
		eventInfoBt.setBackground(new Color(255,255,255));
		eventInfoBt.addActionListener(this);
		
		storeBt=new JButton("store");
		storeBt.setBackground(Color.white);
		storeBt.addActionListener(this);
		
		NADSResultsBt=new JButton("NADSResults");
		NADSResultsBt.setBackground(Color.white);
		NADSResultsBt.addActionListener(this);
		
		deploymentMonitorBt=new JButton("deployment monitor");
		deploymentMonitorBt.setBackground(Color.white);
		deploymentMonitorBt.addActionListener(this);
		
		varTestBt=new JButton("var test");
		varTestBt.setBackground(Color.white);
		varTestBt.addActionListener(this);
		
		//loadBt=new JButton("load");
		//loadBt.setBackground(Color.WHITE);
		//loadBt.addActionListener(this);
		
		implementationCb=new JComboBox(implementationAr);
		implementationCb.setBackground(new Color(255,255,255));
		implementationCb.addItemListener(this);
		
		nodeSizeCb=new JComboBox(nodeSizeAr);
		//nodeSizeCb.setSelectedIndex(6);
		nodeSizeCb.setBackground(new Color(255,255,255));
		nodeSizeCb.addItemListener(this);
		
		rangeCb=new JComboBox();
		rangeCb.addItem("35");
		rangeCb.addItem("70");
		base.range=Integer.parseInt(rangeCb.getSelectedItem().toString());
		rangeCb.setBackground(Color.WHITE);
		rangeCb.addItemListener(this);
		
		
		//simulatorTb.add(newBt);
		//simulatorTb.addSeparator();
		simulatorTb.add(clearBt);
		simulatorTb.addSeparator();
		simulatorTb.add(runBt);
		simulatorTb.addSeparator();
		//simulatorTb.add(stopBt);
		//simulatorTb.addSeparator();
		//simulatorTb.add(graphBt);
		//simulatorTb.addSeparator();
		//simulatorTb.add(printBt);
		//simulatorTb.addSeparator();
		simulatorTb.add(implementationCb);
		simulatorTb.addSeparator();
		simulatorTb.add(baseControlRoomBt);
		simulatorTb.addSeparator();
		simulatorTb.add(viewBt);
		simulatorTb.addSeparator();
		simulatorTb.add(nodeSizeCb);
		simulatorTb.addSeparator();
		simulatorTb.add(eventInfoBt);
		
		simulatorTb.addSeparator();
		simulatorTb.add(rangeCb);
		
		simulatorTb.addSeparator();
		simulatorTb.add(storeBt);
		simulatorTb.addSeparator();
		simulatorTb.add(NADSResultsBt);
		simulatorTb.addSeparator();
		simulatorTb.add(deploymentMonitorBt);
		simulatorTb.add(varTestBt);
		//simulatorTb.add(loadBt);
		
		northPanel.add(simulatorTb);
		
		//###################STARTING THE SOUTH PANEL####################
		southPanel=new JPanel();
		southPanel.setLayout(new GridLayout(2,1));
		
		upperSouthPanel=new JPanel();
		upperSouthPanel.setBackground(new Color(244,244,244));
		upperSouthPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		baseMb=new JMenuBar();
		fileMenu=new JMenu();
		fileMenu.setIcon(new ImageIcon("images/titlelogo.png"));
		scriptEditorMi=new JMenuItem("script editor");
		packetEditorMi=new JMenuItem("packet editor");
		figEditorMi=new JMenuItem("figure editor");
		pathEditorMi=new JMenuItem("path editor");
		progCommentEditorMi=new JMenuItem("comment editor");
		openMi=new JMenuItem("open");
		JMenuItem newMi=new JMenuItem("new");
		JMenuItem newMi2=new JMenuItem("new2");
		JMenuItem newMi3=new JMenuItem("new3");
		
		
		
		scriptEditorMi.addActionListener(this);
		openMi.addActionListener(this);
		packetEditorMi.addActionListener(this);
		figEditorMi.addActionListener(this);
		pathEditorMi.addActionListener(this);
		progCommentEditorMi.addActionListener(this);
		
		
		fileMenu.add(scriptEditorMi);
		fileMenu.add(packetEditorMi);
		fileMenu.add(figEditorMi);
		fileMenu.add(pathEditorMi);
		fileMenu.add(progCommentEditorMi);
		fileMenu.add(openMi);
		fileMenu.add(newMi);
		fileMenu.add(newMi2);
		fileMenu.add(newMi3);
		
		customizeMenu=new JMenu("customize");
		customizeRunMi=new JMenuItem("customize run");
		customizeRunMi.addActionListener(this);
		customizeMenu.add(customizeRunMi);
		
		toolKitMenu=new JMenu("toolkit");
		basicToolkitMi=new JMenuItem("basic toolkit");
		basicToolkitMi.addActionListener(this);
		toolKitMenu.add(basicToolkitMi);
		
		
		threeDMenu=new JMenu();
		threeDMenu.setIcon(new ImageIcon("images/icon_3d.png"));
		threeDRunMi=new JMenuItem("run 3d");
		threeDRunMi.addActionListener(this);
		threeDMenu.add(threeDRunMi);
		
		windowMenu=new JMenu("window");
		
		graphMenu=new JMenu("Graph");
		showGraphDataMi=new JMenuItem("data");
		showGraphDataMi.addActionListener(this);
		graphMenu.add(showGraphDataMi);
		
		
		baseMb.add(fileMenu);
		baseMb.add(customizeMenu);
		baseMb.add(toolKitMenu);
		baseMb.add(threeDMenu);
		baseMb.add(graphMenu);
		baseMb.add(windowMenu);
		upperSouthPanel.add(baseMb);
		
		
		twoDBt=new JButton("2-D");
		twoDBt.addActionListener(this);
		
		
		lowerSouthPanel=new JPanel();		
		
		lowerSouthPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		dAShapeLb=new JLabel("deployment area shape:");
		dAShapeCb=new JComboBox();
		dAShapeCb.addItem("square");
		dAShapeCb.addItem("circle");
		dAShapeCb.addItemListener(this);
		dimensionLb=new JLabel("dimensions");
		radiusLb=new JLabel("radius");
		dRadiusTf=new JTextField("250",5);
		dRadiusTf.setEnabled(false);
		dxTf=new JTextField("500",5);
		dyTf=new JTextField("500",5);
		
		scaleLb=new JLabel("scale : ");
		scaleTf=new JTextField("1",3);
		
		mousePositionLb=new JLabel("       position:");
		mxLb=new JLabel("0");
		myLb=new JLabel("0");
		
		eventPositionLb=new JLabel("   	    event location:");
		exLb=new JLabel("0");
		eyLb=new JLabel("0");
		
		
		
		lowerSouthPanel.add(dAShapeLb);
		lowerSouthPanel.add(dAShapeCb);
		lowerSouthPanel.add(radiusLb);
		lowerSouthPanel.add(dRadiusTf);
		
		lowerSouthPanel.add(dxTf);
		lowerSouthPanel.add(new JLabel("X"));
		lowerSouthPanel.add(dyTf);
		
		lowerSouthPanel.add(scaleLb);
		lowerSouthPanel.add(scaleTf);
		
		lowerSouthPanel.add(mousePositionLb);
		lowerSouthPanel.add(mxLb);
		lowerSouthPanel.add(new JLabel(" , "));
		lowerSouthPanel.add(myLb);
		
		lowerSouthPanel.add(eventPositionLb);
		lowerSouthPanel.add(exLb);
		lowerSouthPanel.add(new JLabel(" , "));
		lowerSouthPanel.add(eyLb);
		
		
		
		southPanel.add(upperSouthPanel);
		southPanel.add(lowerSouthPanel);
	
		
		//******************STARTING THE CENTER PNAEL********************
		centerPanel=new WorkAreaPanel(this);
		
		//*******************STARTING THE WEST PANEL********************
		
		//westPanel=new ToolKitPanel(base);
		toolKitDialog=new BasicToolKitDialog(base);
		toolKitDialog.setBounds(900,70,150,620);
		toolKitDialog.setVisible(true);
		//toolKitDialog.setResizable(false);

		//*******************STARTING THE EAST PANEL********************
		JPanel eastPanel=new JPanel();
		
		//*******************ADDING THE PANEL***************************
		
		this.add("Center",centerPanel);
		this.add("North",northPanel);
		this.add("South",southPanel);
		//this.add("West",westPanel);	
	}
	
	
	//*************************IMPLEMENTING ACTION EVENTS******************
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==newBt)
		{
			//System.out.println("new button have been pressed");
		}
		
		if(ae.getSource()==clearBt)
		{
			//System.out.println("clear button have been presed");
			clearDa();	
		}
		
		if(ae.getSource()==runBt)
		{
			//System.out.println("satrt button pressed");
			//centerPanel.fun();
			Runnable r=new Runnable()
			{
				public void run()
				{					
					RunConfig rf=new RunConfig(base);
					rf.setBounds(100,100,300,350);
					rf.setVisible(true);							
				}
			};
			new Thread(r).start();									
		}
		
		if(ae.getSource()==stopBt)
		{
			System.out.println("stop button pressed");			
		}
		
		if(ae.getSource()==graphBt)
		{
			int a[]={0,200,300,400,500};
			int b[]={0,200,300,400,500};
			//graphPanel.canMakeGraph=true;
			//graphPanel.plotGraph(a,b,a.length,Color.red);
			//System.out.println("graph button have been pressed");
			base.remove(centerPanel);

			graphPanel.add(graphPanel.minBt);
			graphPanel.add(graphPanel.closeBt);
			graphPanel.add(graphPanel.popOutBt);			
			base.add("Center",graphPanel);
			graphPanelBt[0]=new JButton("graph 1 ^");
			graphPanelBt[0].setBackground(Color.GRAY);
			graphPanelBt[0].addActionListener(base);
			upperSouthPanel.add(graphPanelBt[0]);
			upperSouthPanel.revalidate(); 
		}
		
		
		if(ae.getSource()==printBt)
		{
			//System.out.println("print button have been pressed");
		}
		if(ae.getSource()==graphPanelBt[0])
		{
			base.remove(centerPanel);
			graphPanel.add(graphPanel.minBt);
			graphPanel.add(graphPanel.closeBt);
			graphPanel.add(graphPanel.popOutBt);
			graphPanel.revalidate();
			base.add("Center",graphPanel);
			base.repaint();
			//revalidate();
		}
		
		if(ae.getSource()==graphPanelBt[1])
		{
			base.remove(centerPanel);
			graphPanel2.add(graphPanel2.minBt);
			graphPanel2.add(graphPanel2.closeBt);
			graphPanel2.add(graphPanel2.popOutBt);
			graphPanel2.revalidate();
			base.add("Center",graphPanel2);
			base.repaint();
			//revalidate();
		}
		
		if(ae.getSource()==baseControlRoomBt)
		{
			try
			{
				baseStationControlRoom=new BaseStationControlRoom(base);			
				baseStationControlRoom.setVisible(true);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				base.alert.display("base station not deployed");
			}
		}
		
		if(ae.getSource()==viewBt)
		{
			ViewDialog vd=new ViewDialog(base);
			vd.setBounds(1000,100,300,400);
			vd.setVisible(true);
		}
		
		if(ae.getSource()==eventInfoBt)
		{
			VInfoDialog vInfoDialog=new VInfoDialog(base);
			vInfoDialog.setVisible(true);
		}
		
		if(ae.getSource()==storeBt)
		{
			Runnable r=new Runnable()
			{
				public void run()
				{
					try
					{
						base.joint.putData("delete from deployment");
						
						/*for(int i=0;i<base.nodeCount;i++)
						{
							int x=base.nodeArray[i].getX();
							int y=base.nodeArray[i].getY();
							int commRange=base.nodeArray[i].commRange;
							int sensingRange=base.nodeArray[i].sensingRange;
							int power=base.nodeArray[i].power;
							int bufferSize=base.nodeArray[i].defaultBufferSize;
							
							String query="insert into deployment values("+x+","+y+","+commRange+","+sensingRange+","+power+","+bufferSize+")";
							base.joint.putData(query);
						}*/
						
						for(int i=0;i<masterNodeList.size();i++)
						{
							int x=masterNodeList.get(i).getX();
							int y=masterNodeList.get(i).getY();
							float commRange=masterNodeList.get(i).commRange;
							float sensingRange=masterNodeList.get(i).sensingRange;
							double power=masterNodeList.get(i).power;
							int bufferSize=masterNodeList.get(i).defaultBufferSize;
							
							String query="insert into deployment values("+x+","+y+","+commRange+","+sensingRange+","+power+","+bufferSize+")";
							base.joint.putData(query);
						}						
					}
					catch(Exception e){e.printStackTrace();}
				}
			};
			new Thread(r).start();
		}
		
		if(ae.getSource()==NADSResultsBt)
		{
			NADSResults dhds2=new NADSResults(base);
			dhds2.setBounds(200,100,800,600);
			dhds2.setVisible(true);
		}
		
		if(ae.getSource()==deploymentMonitorBt)
		{
			GadsDeploymentControlDialog dmp=new GadsDeploymentControlDialog(base);
			dmp.setBounds(800,200,600,500);
			dmp.setVisible(true);
			//gadsDeploymentControlDialog.setBounds(600,200,600,500);
			//gadsDeploymentControlDialog.setVisible(true);	
		}
		
		if(ae.getSource()==varTestBt)
		{
			//varTestDialog=new VarTestDialog(base);
			varTestDialog.setBounds(650,150,400,500);
			varTestDialog.setVisible(true);
		}

		//--------------HANDELING ACTION EVENTS FOR MENU ITEMS---------------------------
			if(ae.getSource()==scriptEditorMi)
			{
				scriptEditor.setBounds(900,20,900,700);
				scriptEditor.setVisible(true);
			}
			
			if(ae.getSource()==packetEditorMi)
			{
				VPacketEditorDialog packetEditor=new VPacketEditorDialog(base);
				packetEditor.setBounds(900,20,900,700);
				packetEditor.setVisible(true);
			}
			
			if(ae.getSource()==figEditorMi)
			{
				//VFigEditorDialog figEditor=new VFigEditorDialog(base);
				figEditorDialog.setBounds(900,20,900,700);
				figEditorDialog.setVisible(true);
			}
			
			if(ae.getSource()==pathEditorMi)
			{
				//VFigEditorDialog figEditor=new VFigEditorDialog(base);
				pathEditorDialog.setBounds(900,20,900,700);
				pathEditorDialog.setVisible(true);
			}
			
			if(ae.getSource()==progCommentEditorMi)
			{
				progCommentEditor.setBounds(900,20,900,700);
				progCommentEditor.setVisible(true);
			}

			if(ae.getSource()==openMi)
			{
				System.out.println("open as button have been pressed");
				JFileChooser fileChooser=new JFileChooser();
				JDialog fileChooserDialog=new JDialog();
				fileChooserDialog.add(fileChooser);
				fileChooserDialog.setSize(600,400);
				fileChooserDialog.setVisible(true);
			}
			
			if(ae.getSource()==customizeRunMi)
			{
				Runnable r=new Runnable()
				{
					public void run()
					{
						ThreadControlDialog tcd=new ThreadControlDialog(base);
						tcd.setBounds(200,200,400,500);
						tcd.setVisible(true);
					}
				};
				new Thread(r).start();
				
			}
			
			if(ae.getSource()==threeDRunMi)
			{
				Runnable r=new Runnable()
				{
					public void run()
					{
						ThreeDWorkAreaDialog tdd=new ThreeDWorkAreaDialog(base);
						//tdd.setBounds(10,10,base.getWidth(),base.getHeight());
						tdd.setBounds(690,60,450,400);
						tdd.setVisible(true);
						tdd.repaint();
						tdd.revalidate();
						
						
						//System.out.println("three d run menuitem has been selected");
						base.remove(base.centerPanel);
						//ThreeDWorkAreaPanel threeDWorkAreaPanel=new ThreeDWorkAreaPanel(base);
						//base.add("Center",threeDWorkAreaPanel);
						//base.revalidate();
						
						try{Thread.sleep(5000);}catch(Exception e){}
						System.out.println("three d run menuitem has been selected");
						//base.remove(threeDWorkAreaPanel);
						base.add("Center",centerPanel);
						base.repaint();
						base.revalidate();
						
						//upperSouthPanel.add(twoDBt);
						//upperSouthPanel.revalidate();
					}
				};
				new Thread(r).start();		
				
			}
			
			if(ae.getSource()==showGraphDataMi)
			{
				ShowGraphDataDialog sgdd=new ShowGraphDataDialog(base);
				sgdd.setBounds(200,200,600,500);
				sgdd.setVisible(true);
			}
			
			if(ae.getSource()==basicToolkitMi)
			{
				toolKitDialog.setBounds(900,70,150,620);
				toolKitDialog.setVisible(true);
				toolKitDialog.setResizable(false);
			}
			
/*			if(ae.getSource()==twoDBt)
			{
				Runnable r=new Runnable()
				{
					public void run()
					{
						System.out.println("two d button have been pressed by vikrant sharma");
						JDialog twoDDialog=new JDialog();
						twoDDialog.setBounds(100,100,1000,700);
						twoDDialog.add(base.centerPanel);
						twoDDialog.setVisible(true);
					}
				};
				new Thread(r).start();
			}
		*/
		//-----------------------------------------------------------------------------
				
	}
	
	
	
	
	
	
	//*****************IMPLEMENTING ITEM LISTENER**************************
	public void itemStateChanged(ItemEvent ie)
	{
		if(ie.getSource()==dAShapeCb)
		{
			if(dAShapeCb.getSelectedItem()=="circle")
			{
				dRadiusTf.setEnabled(true);
				dxTf.setEnabled(false);;
				dyTf.setEnabled(false);
			}
			
			if(dAShapeCb.getSelectedItem()=="square")
			{
				dRadiusTf.setEnabled(false);
				dxTf.setEnabled(true);
				dyTf.setEnabled(true);
			}
			
		}
		
		if(ie.getSource()==implementationCb)
		{
			//System.exit(0);
			if(implementationCb.getSelectedItem()=="flooding")
			{
				activeImplementation=this.FLOODING;
			}
			
			if(implementationCb.getSelectedItem()=="directional flooding")
			{
				activeImplementation=this.DIRECTIONAL_FLOODING;
			}
			if(implementationCb.getSelectedItem()=="directed diffusion")
			{
				activeImplementation=this.DIR_DIFFUSION;
			}
			
			
			
		}
		
		if(ie.getSource()==nodeSizeCb)
		{
			try
			{   
				nodeSize=Integer.parseInt(nodeSizeCb.getSelectedItem().toString());
				/*for(int i=0;i<base.nodeCount;i++)
				{
					base.nodeArray[i].setBounds(base.nodeArray[i].getX(),base.nodeArray[i].getY(),nodeSize,nodeSize);
					base.nodeArray[i].revalidate();
				}*/
				
				for(int i=0;i<masterNodeList.size();i++)
				{
					masterNodeList.get(i).setBounds(masterNodeList.get(i).getX(),masterNodeList.get(i).getY(),nodeSize,nodeSize);
					masterNodeList.get(i).revalidate();
				}
			}
			catch(Exception e){System.out.println("base  line  no  470 :"+e);}
		}
		
		if(ie.getSource()==rangeCb)
		{
			try
			{
				base.range=Integer.parseInt(rangeCb.getSelectedItem().toString());
			}catch(Exception e){}
		}
	}
	
	//----------------------------------------------------------------------
	

	//***************FUNCTIONS*********************************************

	//-------------------------CLEARING THE DEPLOYMENT AREA------------------------
	void clearDa()
	{
		base.mstConnectionPairList.removeAll(base.mstConnectionPairList);
		for(int i=0;i<base.masterNodeList.size();i++)
		{
			/*nodeArray[i].xPos=0;
			nodeArray[i].yPos=0;
			nodeArray[i].nodeType=-1;
			nodeArray[i].power=0;
			nodeArray[i].uniqueId=0;
			nodeArray[i].sensing=false;
			nodeArray[i].working=false;
			//System.out.println(i);*/
			
			masterNodeList.get(i).xPos=0;
			masterNodeList.get(i).yPos=0;
			masterNodeList.get(i).nodeType=-1;
			masterNodeList.get(i).power=0;
			masterNodeList.get(i).uniqueId=0;
			masterNodeList.get(i).sensing=false;
			masterNodeList.get(i).working=false;
		}
		
		
		base.baseProperties.hideConnection();
		try
		{
			//base.baseStationArray[0].neighborList.removeAll(base.baseStationArray[0].neighborList);
			base.masterBSList.get(0).neighborList.removeAll(base.masterBSList.get(0).neighborList);
			
		}
		catch(NullPointerException e){}
		
		base.sourceNode=false;
		base.sinkNode=false;
		base.eventFlag=false;
		base.radius=0;
		base.centerPanel.eventCondition=-1;
		//base.baseStationArray=null;
		base.masterBSList.removeAll(base.masterBSList);
		
		base.xEventOuterList.removeAll(base.xEventOuterList);
		base.xEventInnerList.removeAll(base.xEventInnerList);
		base.xEventMiddleList.removeAll(base.xEventMiddleList);
		
		base.yEventOuterList.removeAll(base.yEventOuterList);
		base.yEventInnerList.removeAll(base.yEventInnerList);
		base.yEventMiddleList.removeAll(base.yEventMiddleList);
		
		base.centerPanel.clearCoordinateMat(baseHeight,baseWidth);
		base.centerPanel.removeAll();
		//base.centerPanel.add(base.centerPanel.infoPanel);
		//base.westPanel.propertiesDialog.nodeLb=null;
		base.centerPanel.revalidate();
	}

	//*********************************************************************
	
	//******************FUNCTION FOR BREAKING NODE CONNECTIONS*************
	public void connectionBreaker(ArrayList<VLabel>nodeList)
	{
		for(int i=0;i<nodeList.size();i++)
		{
			nodeList.get(i).connectionList.removeAll(nodeList.get(i).connectionList);
			for(int j=0;j<nodeList.get(i).neighborList.size();j++)
			{
				nodeList.get(i).connectionList.add(false);
			}
		}
	}
	//--------------------------------------------------------------------
	

	//*********************************************************************
	public static void main(String args[])
	{
		
		Runnable r=new Runnable()
		{
			public void run()
			{
				Base frame=new Base();
				frame.setVisible(true);
			}
		};
		new Thread(r).start();
		

	}

}
