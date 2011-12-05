package Raes
import grails.converters.JSON

import grails.converters.JSON
import jofc2.OFC
import jofc2.model.Chart
import jofc2.model.axis.XAxis
import jofc2.model.axis.YAxis
import jofc2.model.elements.FilledBarChart

import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH
import org.hibernate.Hibernate

import raes.Rae
import raes.Tools
import raes.University
import raes.User
import raes.Category
class ReportController {

	
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	static layout='main'
	def springSecurityService
	def sessionFactory
	def accessLogService

	def index = {
		def sessionUser = springSecurityService.getCurrentUser()?.id;
		def server = CH.config.grails.serverURL
		if(sessionUser ==null)
			redirect(url:CH.config.grails.serverURL+"/login/auth")
		else{
			def accessLog =  accessLogService.createAccessLog(sessionUser, "/rae/report")
			[accessLog:accessLog,server:server, user:User.get(sessionUser)]
		}
		
		
	}
	
	
	
	//universidades, año, palabras clave y metodologias
	
	
	def generateUniversityChart={
		
		def universities = University.list()
		
		def universitySeries = []
		def universityLabels = []
		universities.each{
			def number = Rae.countByUniversity(it)
			def percentage = (int)((number /Rae.count())*100)
			
			def name = it.name.split(" ")
			 
			def finalName = ""
			
			name.each{
				def chartArray = it.toCharArray()
				if(chartArray.size()>0)
				finalName = finalName+chartArray[0]
			}
			
			
			universityLabels.add(finalName+"-"+percentage+"%" )
			universitySeries.add(number)
			
		}
		def universityChart = _buildChart("Universidades",[universitySeries], universityLabels,Rae.count())
		
		render JSON.parse (universityChart) as JSON
	}
	
	
	def universitiesLabels={
		def universities = University.list()
		def universityLabelslist =[]
		universities.each{
			
			def name = it.name.split(" ")
			 
			def finalName = ""
			
			name.each{
				def chartArray = it.toCharArray()
				if(chartArray.size()>0)
				finalName = finalName+chartArray[0]
			}
			
			
			universityLabelslist.add(finalName+"-"+it.name)
		}
		[universitiesLabels:universityLabelslist]
		
	}
	
	def generateYearsChart={
		
		def raes = Rae.createCriteria().list{
			
			order('year',"ASC")
		}
		
		def yearsLabels =[]
		def yearsSeries =[]
		
		def flag=[]
		
		raes.each{ 
			if(!flag.contains(it.year.toString())){
				def number = Rae.countByYear(it.year)
				
				def percentage = (int)((number /Rae.count())*100)
			
				yearsLabels.add(it.year.toString()+"-"+percentage+"%")
				yearsSeries.add(number)
				flag.add(it.year.toString())
			}
			
			
		}
		
		def yearsChart = _buildChart("Tiempo",[yearsSeries], yearsLabels,Rae.count())
		
		render JSON.parse (yearsChart) as JSON
		
		
	}
	
	
	def generateMetodologyChart={
		def raes = Rae.createCriteria().list{
			
			order('year',"ASC")
		}
		
		
		def methodologyLabels =[]
		def methodologySeries =[]
		
		def flag=[]
		
		raes.each{
			
			if(!flag.contains(it.methodology)){
				
				def number = Rae.countByMethodology(it.methodology)
				def percentage = (int)((number /Rae.count())*100)
				
				def name = it.methodology.toString().split(" ")
				
			   def finalName = ""
			   
			   name.each{
				   def chartArray = it.toCharArray()
				   if(chartArray.size()>0)
				   finalName = finalName+chartArray[0]
			   }
			   
			
				methodologyLabels.add(finalName+"-"+percentage+"%")
				methodologySeries.add(number)
				flag.add(it.methodology)
			}
			
		}
		
	
		def methodologyChart = _buildChart("Metodologias",[methodologySeries], methodologyLabels,Rae.count())
		
		
		render JSON.parse (methodologyChart) as JSON
		
	}
	def generateMethodologyLabels={
		
		
		def methodologies =Rae.createCriteria().list{
			
			order('year',"ASC")
		}
		
		
		def methodologyLabels =[]
		methodologies.unique{it.methodology}.each{
			
			
			def name = it.methodology.toString().split(" ")
			
		   def finalName = ""
		   
		   name.each{
			   def chartArray = it.toCharArray()
			   if(chartArray.size()>0)
			   finalName = finalName+chartArray[0]
		   }
		   
		   if(!(it.methodology.toString().equals("")))
			methodologyLabels.add(finalName+"-"+it.methodology.toString())
			
		}
		
		[methodologyLabels:methodologyLabels]
	
	}
	
	def generateKeyWordsChart={
		
		def universities = Category.list()
		
		def universitySeries = []
		def universityLabels = []
		universities.each{
			def number = Rae.countByCategory(it)
			def percentage = (int)((number /Rae.count())*100)
			
			
			
			def name = it.name.toString().split(" ")
			
		   def finalName = ""
		   
		   name.each{
			   def chartArray = it.toCharArray()
			   if(chartArray.size()>0)
			   finalName = finalName+chartArray[0]
		   }
		   	
			universityLabels.add(finalName+"-"+percentage+"%" )
			universitySeries.add(number)
			
		}
		def universityChart = _buildChart("Categorias",[universitySeries], universityLabels,Rae.count())
		
		render JSON.parse (universityChart) as JSON
		
		
		
	}
	
	def categoriesList={
		def categories = Category.list()
		
		def categoriesLabelslist =[]
		categories.each{
			
			def name = it.name.split(" ")
			 
			def finalName = ""
			
			name.each{
				def chartArray = it.toCharArray()
				if(chartArray.size()>0)
				finalName = finalName+chartArray[0]
			}
			
			
			categoriesLabelslist.add(finalName+"-"+it.name)
		}
		[categoriesLabels:categoriesLabelslist]
		
		
	}
	
	
	def generateToolsChart={
		
		def keyWordsLabels =[]
		def keyWordsSeries =[]
		
		def sql = '''
			select count(tools_id) as counterValue , rae.tools.name as toolName from rae.rae_tools as raeTools
			inner join rae.tools as tools on tools.id = raeTools.tools_id
			group by raeTools.tools_id

		'''
		
		def currentSession = sessionFactory.currentSession
		def queryProfile =  currentSession.createSQLQuery(sql);
		
		queryProfile.addScalar("counterValue",Hibernate.INTEGER);
		queryProfile.addScalar("toolName",Hibernate.STRING);
		
		def results = queryProfile.list();

		results.each{
			
			def number = it[0]
			def percentage = (int)((number /Tools.count())*100)
			
			def name = it[1].split(" ")
			
		   def finalName = ""
		   
		   name.each{
			   def chartArray = it.toCharArray()
			   if(chartArray.size()>0)
			   finalName = finalName+chartArray[0]
		   }
			
			
			
			keyWordsLabels.add(finalName)
			keyWordsSeries.add(it[0])
		}
		
		
		def keyWordsChart = _buildChart("Herramientas",[keyWordsSeries], keyWordsLabels,Rae.count())
		
		render JSON.parse (keyWordsChart) as JSON
		
		
		
	}
	
	
	def generateToolsNamesChart={
		
		def toolsNames = Tools.list()
		def keyWordsLabels =[] 
		toolsNames.each {
			
			def name = it.name.split(" ")
			
		   def finalName = ""
		   
		   name.each{
			   def chartArray = it.toCharArray()
			   if(chartArray.size()>0)
			   finalName = finalName+chartArray[0]
		   }
			
			keyWordsLabels.add(finalName+"-"+it.name)
			
		}
		[keyWordsLabels:keyWordsLabels]
		
	}
	
	
	
	
	
	def _buildChart(def title, def series=[], def labels=[], def top) {
		def ofc = OFC.getInstance()
		//if(title.length() >27)
			//title = wu.wrap(title,27,"\n",false)
		
		def chart = new Chart(title, "font-size:11px;white-space:normal;")
		

		series.each { serie->
			def graph = new FilledBarChart()
			graph.setOutlineColour("#9EC3E6")
			graph.setColour("#00FF00")
			graph.addValues(serie)
			chart.addElements(graph)
		}

		def yAxis = new YAxis()
		def xAxis = new XAxis()


		yAxis.setGridColour("#C0C0C0")
		yAxis.setColour("#FFFFFF")
		yAxis.setMax(top)
		yAxis.setMin(0)
		yAxis.setSteps(5)
		xAxis.setGridColour("#C0C0C0")
		xAxis.setColour("#C0C0C0")
		xAxis.setLabels( labels)

		chart.setYAxis(yAxis)
		chart.setXAxis(xAxis)
		chart.setBackgroundColour("#F6F6F6");
		//chart.computeYAxisRange(1)

		return ofc.render( chart)

	}
	
	
}
