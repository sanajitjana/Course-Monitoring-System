package com.cms.dao;

import java.util.List;

import com.cms.bean.Batch;
import com.cms.bean.ReportForBatchDTO;
import com.cms.exceptions.BatchException;

public interface BatchDao {

	// checking course id present or not
	public boolean isCourseIdPresent(int courseId) throws BatchException;

	// checking faculty id present or not
	public boolean isFacultyIdPresent(int facultyId) throws BatchException;

	// checking batch name previously present or not
	public boolean isBatchNameUnique(String name) throws BatchException;

	// create batch
	public String createBatch(Batch batch) throws BatchException;

	// update batch by name
	public String upadteBatchByName(String old_name, Batch batch) throws BatchException;

	// view all batch details
	public List<Batch> viewAllBatchDetails() throws BatchException;

	// delete batch by name
	public String batchDeleteByName() throws BatchException;

	// view course plan report for every batch
	public List<ReportForBatchDTO> coursePlanReportForBatch() throws BatchException;
	
	//all batches
	public List<Batch> availableBatch() throws BatchException;

}
