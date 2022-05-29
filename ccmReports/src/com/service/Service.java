package com.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.bean.H1QueryBean;
import com.bean.NewlyOpenedHOBean;
import com.bean.NewlyOpenedTicketsBean;
import com.bean.Reopen10To3pmBean;
import com.bean.Reopen3To6pmBean;
import com.bean.ReopenedBean;
import com.bean.ResolvedByHO_OFWeeklyBean;
import com.bean.ResolvedByHoBean;
import com.bean.ResolvedByTicketsBean;
import com.bean.ResolvedbyTCS_OFWeeklyBean;
import com.bean.ReturnAfterClarificationBean;
import com.bean.SentForClarificationBean;
import com.bean.SentForClarificationHO_NEW_OFWeeklyBean;
import com.bean.SentForClarificationTCS_NEW_OFWeeklyBean;
import com.bean.aginingWithIPBean;
import com.bean.updated10amQueryBean;
import com.bean.updated3pmQueryBean;
import com.bean.updated_3pm_OF_3To6Bean;
import com.bean.updated_6pm_OF_3To6Bean;
import com.dao.dao;
public class Service {
		dao d = new dao();
		
		public ArrayList<aginingWithIPBean> AgingwithipList() throws FileNotFoundException, IOException {
			System.out.println("in service");
			/*aginingWithIPdao Dao=new aginingWithIPdao();*/
			return dao.getAgingwithwipDao();
					
		}
		
		public ArrayList<H1QueryBean> fetchdetailsfromDB() throws IOException{
			/*H1QueryDao fetch = new H1QueryDao();*/
			return dao.getH1QueryDao();
		}
		
		public ArrayList<NewlyOpenedHOBean> NewlyOpenedHOService() throws IOException{
			/*NewlyOpenedHODao NewlyOpenedHODao = new NewlyOpenedHODao();*/
			return dao.getNewlyOpenedHODao();
			
		}
		
		public ArrayList<NewlyOpenedTicketsBean> NewlyOpenedTicketsService() throws IOException{
			/*NewlyOpenedTicketsDao NewlyOpenedTicketsDao = new NewlyOpenedTicketsDao();*/
			return dao.getNewlyOpenedTicketsDao();
			
		}
		
		
		public ArrayList<Reopen10To3pmBean> Reopen10To3pmService() {
			/*Reopen10To3pmDao Reopen10To3pmDao = new Reopen10To3pmDao();*/
			return dao.getReopen10To3pmDao();
		}
		
		public ArrayList<Reopen3To6pmBean> Reopen3To6pmService() {
			/*Reopen3To6pmDao Reopen3To6pmDao = new Reopen3To6pmDao();*/
			return dao.getReopen3To6pmDao();
		}

		public ArrayList<ReopenedBean> ReopenedService() throws IOException {
			/*ReopenedDao ReopenedDao = new ReopenedDao();*/
			return dao.getReopenedDao();
		}
		
		public ArrayList<ResolvedByHO_OFWeeklyBean> ResolvedByHO_OFWeeklyService() throws IOException {
			/*ResolvedByHO_OFWeeklyDao ResolvedByHO_OFWeeklyDao = new ResolvedByHO_OFWeeklyDao();*/
			return dao.getResolvedByHO_OFWeeklyDao();
		}
		
		public ArrayList<ResolvedByHoBean> ResolvedByHoService() throws IOException {
			/*ResolvedByHoDao ResolvedByHoDao = new ResolvedByHoDao();*/
			return dao.getResolvedByHoDao();
		}
		
		public ArrayList<ResolvedbyTCS_OFWeeklyBean> ResolvedbyTCS_OFWeeklyService() throws IOException {
			/*ResolvedbyTCS_OFWeeklyDao ResolvedbyTCS_OFWeeklyDao = new ResolvedbyTCS_OFWeeklyDao();*/
			return dao.getResolvedbyTCS_OFWeeklyDao();
		}
		
		public ArrayList<ResolvedByTicketsBean> ResolvedByTicketsService() throws IOException {
			/*ResolvedByTicketsDao ResolvedByTicketsDao = new ResolvedByTicketsDao();*/
			return d.getResolvedByTicketsDao();
			
		}
		
		public ArrayList<ReturnAfterClarificationBean> ReturnAfterClarificationService() {
			/*ReturnAfterClarificationDao ResolvedByTicketsDao = new ReturnAfterClarificationDao();*/
			return d.getReturnAfterClarificationDao();
		}
		
		public ArrayList<SentForClarificationHO_NEW_OFWeeklyBean> SentForClarificationHO_NEW_OFWeeklyService() {
			/*SentForClarificationHO_NEW_OFWeeklyDao ResolvedByTicketsDao = new SentForClarificationHO_NEW_OFWeeklyDao();*/
			return dao.getSentForClarificationHO_NEW_OFWeeklyDao();
		}
		
		public ArrayList<SentForClarificationBean> SentForClarificationService() {
			/*SentForClarificationDao ResolvedByTicketsDao = new SentForClarificationDao();*/
			return dao.getSentForClarificationDao();
		}
		public ArrayList<SentForClarificationTCS_NEW_OFWeeklyBean> SentForClarificationTCS_NEW_OFWeeklyService() {
			/*SentForClarificationTCS_NEW_OFWeeklyDao ResolvedByTicketsDao = new SentForClarificationTCS_NEW_OFWeeklyDao();*/
			return dao.getSentForClarificationTCS_NEW_OFWeeklyDao();
		}
		public ArrayList<updated_3pm_OF_3To6Bean> updated_3pm_OF_3To6Service() {
			/*updated_3pm_OF_3To6Dao updated_3pm_OF_3To6Dao = new updated_3pm_OF_3To6Dao();*/
			return dao.getupdated_3pm_OF_3To6Dao();
		}
		public ArrayList<updated_6pm_OF_3To6Bean> updated_6pm_OF_3To6Service() {
			/*updated_6pm_OF_3To6Dao updated_6pm_OF_3To6Dao = new updated_6pm_OF_3To6Dao();*/
			return dao.getupdated_6pm_OF_3To6Dao();
		}
		public ArrayList<updated10amQueryBean> updated10amQueryService() {
			/*updated10amQueryDao updated10amQueryDao = new updated10amQueryDao();*/
			return dao.getupdated10amQueryDao();
		}
		public ArrayList<updated3pmQueryBean> updated3pmQueryService() {
			/*updated3pmQueryDao updated3pmQueryao = new updated3pmQueryDao();*/
			return dao.getupdated3pmQueryDao();
		}


}
