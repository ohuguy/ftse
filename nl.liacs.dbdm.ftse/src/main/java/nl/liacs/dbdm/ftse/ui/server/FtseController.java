/*
 *
 * Created on Dec 27, 2009 | 3:35:56 PM
 *
 */
package nl.liacs.dbdm.ftse.ui.server;

import java.util.Date;

import javax.annotation.Resource;

import nl.liacs.dbdm.ftse.learn.FtseStockService;
import nl.liacs.dbdm.ftse.ui.client.FtseService;
import nl.liacs.dbdm.ftse.ui.client.LikelihoodOptions;
import nl.liacs.dbdm.ftse.ui.client.PredictionOptions;
import nl.liacs.dbdm.ftse.ui.client.PredictionResult;
import nl.liacs.dbdm.ftse.ui.client.TrainingOptions;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 
 * 
 * @author Behrooz Nobakht [bnobakht@liacs.nl]
 **/
@Controller
@RequestMapping(value = { FtseService.URL })
public class FtseController extends BaseSpringGwtController implements FtseService {

	private static final long serialVersionUID = 1L;

	@Resource
	@Qualifier("ftseStockService")
	private FtseStockService service;

	@Override
	public String train(TrainingOptions options) {
		return service.train(options);
	}

	@Override
	public PredictionResult predict(PredictionOptions options) {
		return service.predict(options);
	}

	@Override
	public String updateLikelihoods(LikelihoodOptions options) {
		return service.updateLikelihoods(options);
	}

	@Override
	public String clearData() {
		try {
			service.clearData();
			return "Data reset complete";
		} catch (Exception e) {
		}
		return "Data reset failed";
	}

	@Override
	public String loadData(Date from, Date to) {
		try {
			service.loadData(from, to);
			return "Load data complete";
		} catch (Exception e) {
		}
		return "Failed to load data!";
	}

	public void setService(FtseStockService service) {
		this.service = service;
	}

}
