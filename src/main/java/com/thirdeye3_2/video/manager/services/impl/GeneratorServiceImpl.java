package com.thirdeye3_2.video.manager.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thirdeye3_2.video.manager.dtos.AudioGenerateContent;
import com.thirdeye3_2.video.manager.dtos.AudioGenerateDto;
import com.thirdeye3_2.video.manager.dtos.AudioGenerateFetcherResponseDto;
import com.thirdeye3_2.video.manager.dtos.ContentVideoDto;
import com.thirdeye3_2.video.manager.dtos.CurrentVideoDto;
import com.thirdeye3_2.video.manager.dtos.NewsDto;
import com.thirdeye3_2.video.manager.dtos.StockDto;
import com.thirdeye3_2.video.manager.dtos.StockGroupDto;
import com.thirdeye3_2.video.manager.dtos.StockPriceFetcherResponseDto;
import com.thirdeye3_2.video.manager.dtos.VideoDetailsDto;
import com.thirdeye3_2.video.manager.dtos.VideoDto;
import com.thirdeye3_2.video.manager.dtos.VideoGenerateFetcherResponseDto;
import com.thirdeye3_2.video.manager.dtos.VideoSettingDto;
import com.thirdeye3_2.video.manager.entities.Stock;
import com.thirdeye3_2.video.manager.enums.NewsMultiMediaType;
import com.thirdeye3_2.video.manager.exceptions.GeneratorFetchException;
import com.thirdeye3_2.video.manager.services.AudioGenerateService;
import com.thirdeye3_2.video.manager.services.ContentVideoService;
import com.thirdeye3_2.video.manager.services.CurrentVideoService;
import com.thirdeye3_2.video.manager.services.GeneratorService;
import com.thirdeye3_2.video.manager.services.HeaderService;
import com.thirdeye3_2.video.manager.services.IntroVideoService;
import com.thirdeye3_2.video.manager.services.NewsService;
import com.thirdeye3_2.video.manager.services.OutroVideoService;
import com.thirdeye3_2.video.manager.services.StockGroupService;
import com.thirdeye3_2.video.manager.services.VideoDetailsService;
import com.thirdeye3_2.video.manager.services.VideoService;
import com.thirdeye3_2.video.manager.services.VideoSettingService;

@Service
public class GeneratorServiceImpl implements GeneratorService {

	private static final Logger logger = LoggerFactory.getLogger(GeneratorServiceImpl.class);
	
	 @Autowired
	 private CurrentVideoService currentVideoService;
	 
	 @Autowired
	 private VideoDetailsService videoDetailService;
	 
	 @Autowired
	 private VideoService videoService;
	 
	 @Autowired
	 private StockGroupService stockGroupService;
	 
	 @Autowired
	 private AudioGenerateService audioGenerateService;
	 
	 @Autowired
	 private VideoSettingService videoSettingService;
	 
	 @Autowired
	 private IntroVideoService introVideoService;
	 
	 @Autowired
	 private OutroVideoService outroVideoService;
	 
	 @Autowired
	 private NewsService newsService;
	 
	 @Autowired
	 private ContentVideoService contentVideoService;
	 
	 @Autowired
	 private HeaderService headerService; 
	 
	 public VideoDto getCurrentVideo()
	 {
		 CurrentVideoDto currentVideoDto = currentVideoService.getCurrentVideo();
		 VideoDto videoDto = videoService.getById(currentVideoDto.getVideoId());
		 return videoDto;
	 }
	 
	 public VideoDetailsDto getCurrentVideoDetails()
	 {
		 VideoDto videoDto = getCurrentVideo();
		 List<VideoDetailsDto> videoDetailsDtos = videoDetailService.getByVideoId(videoDto.getId());
		 if(videoDetailsDtos.size() != 1)
		 {
			 throw new GeneratorFetchException("More then one video details");
		 }
		 return videoDetailsDtos.get(0);
	 }
	 
	 @Override
	 public StockPriceFetcherResponseDto stockPriceFetcher()
	 {
		 VideoDto currentVideo = getCurrentVideo();
		 if(currentVideo.getStockGroup() == null)
		 {
			 throw new GeneratorFetchException("Stock group not updated");
		 }
		 StockGroupDto stockGroupDto = stockGroupService.getById(currentVideo.getStockGroup());
		 if(stockGroupDto == null)
		 {
			 throw new GeneratorFetchException("Stock group not found");
		 }
		 List<String> stockCodes = new ArrayList<>();
		 for(StockDto stock : stockGroupDto.getStocks())
		 {
			 if(stock.getActive())
			 {
				 String stockCode = stock.getStockName();
				 if(stock.getMarketCode() != null && stock.getMarketCode().length() > 0)
				 {
					 stockCode+=("."+stock.getMarketCode());
				 }
				 stockCodes.add(stockCode);
			 }
		 }
		 StockPriceFetcherResponseDto stockPriceFetcherResponseDto = new StockPriceFetcherResponseDto(currentVideo.getDateOfUpload(), currentVideo.getId(), stockCodes);
		 return stockPriceFetcherResponseDto;
	 }
	 
	 @Override
	 public AudioGenerateFetcherResponseDto audioGeneraterFetcher()
	 {
		 AudioGenerateFetcherResponseDto audioGenerateFetcherResponseDto = new AudioGenerateFetcherResponseDto();
		 List<AudioGenerateDto> notGeneratedAudioContent = audioGenerateService.getAllNotGenerated();
		 List<AudioGenerateContent> audioGenerateContents = new ArrayList<>();
		 for(AudioGenerateDto audioGenerateDto : notGeneratedAudioContent)
		 {
			 AudioGenerateContent audioGenerateContent = new AudioGenerateContent();
			 audioGenerateContent.setContent(audioGenerateDto.getContent());
			 audioGenerateContent.setAudioGenerateUuid(audioGenerateDto.getId());
			 audioGenerateContent.setNewsUuid(audioGenerateDto.getForeignKey());
			 audioGenerateContent.setTableName(audioGenerateDto.getTableName());
			 audioGenerateContents.add(audioGenerateContent);
		 }
		 audioGenerateFetcherResponseDto.setContents(audioGenerateContents);
		 return audioGenerateFetcherResponseDto;
	 }
	 
	 @Override
	 public VideoGenerateFetcherResponseDto videoGeneraterFetcher()
	 {
		 VideoGenerateFetcherResponseDto videoGenerateFetcherResponseDto = new VideoGenerateFetcherResponseDto();
		 VideoDto videoDto = getCurrentVideo();
		 videoGenerateFetcherResponseDto.setVideoDto(videoDto);
		 VideoDetailsDto videoDetailsDto = getCurrentVideoDetails();
		 videoGenerateFetcherResponseDto.setVideoDetailsDto(videoDetailsDto);
		 VideoSettingDto videoSettingDto = videoSettingService.getActiveSetting();
		 videoGenerateFetcherResponseDto.setVideoSettingDto(videoSettingDto);
		 if(videoSettingDto.getIntroPresent())
		 {
			 videoGenerateFetcherResponseDto.setIntroVideoDto(introVideoService.getActive());
		 }
		 if(videoSettingDto.getOutroPresent())
		 {
			 videoGenerateFetcherResponseDto.setOutroVideoDto(outroVideoService.getActive());
		 }
		 if(videoSettingDto.getMainVideoPresent())
		 {
			 ContentVideoDto contentVideoDto = contentVideoService.getActive();
			 videoGenerateFetcherResponseDto.setContentVideoDto(contentVideoDto);
			 if(contentVideoDto.getIsNewsText())
			 {
				 List<NewsDto> newsDtos =  newsService.getByVideoDetailsId(videoDetailsDto.getId());
				 List<NewsDto> finalList = new ArrayList();
				 for(NewsDto newsDto : newsDtos)
				 {
					 if(contentVideoDto.getIsNewsImage() && newsDto.getIsImageMultiMediaKeyUploaded() && newsDto.getIsImageMultiMediaKeyUploaded())
					 {
						 finalList.add(newsDto);
					 }
					 else if(contentVideoDto.getIsNewsText() && newsDto.getIsImageMultiMediaKeyUploaded())
					 {
						 finalList.add(newsDto);
					 }
				 }
				 videoGenerateFetcherResponseDto.setNewsDtos(finalList);
			 }
			 if(contentVideoDto.getIsBarRace())
			 {
				 StockGroupDto stockGroupDto = stockGroupService.getById(videoDto.getStockGroup());
				 if(stockGroupDto.getActive())
				 {
					 List<StockDto> stocks = stockGroupDto.getStocks();
					 Map<String, String> stockCodeToNameMap = new HashMap<>();
					 for(StockDto stock : stocks)
					 {
						 if(stock.getActive())
						 {
							 String stockCode = stock.getStockName();
							 if(stock.getMarketCode() != null && stock.getMarketCode().length() > 0)
							 {
								 stockCode+=("."+stock.getMarketCode());
							 }
							 stockCodeToNameMap.put(stockCode, stock.getName());
						 }
					 }
					 videoGenerateFetcherResponseDto.setStockCodeToNameMap(stockCodeToNameMap);
				 }
			 }
			 if(contentVideoDto.getIsHeaderPresent())
			 {
				 videoGenerateFetcherResponseDto.setHeaderDto(headerService.getActive());
			 }
		 }
		 videoGenerateFetcherResponseDto.setCurrentTime(LocalDateTime.now());
		 return videoGenerateFetcherResponseDto;
	 }
}
