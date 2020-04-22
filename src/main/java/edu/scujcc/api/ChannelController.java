package edu.scujcc.api;

import edu.scujcc.model.Channel;
import edu.scujcc.service.ChannelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/channel")
public class ChannelController {
    public static final Logger logger = LoggerFactory.getLogger(ChannelController.class);


    @Autowired
    private ChannelService service;


    @GetMapping
    public List<Channel> getAllChannels() {
        logger.info("正在查找所有频道信息");
        List<Channel> results = service.getAllChannels();
        logger.debug("所有频道的数量是："+results.size());
        return results;

    }

    @GetMapping("/{id}")
    public Channel getChannel(@PathVariable String id) {
        logger.info("正在查找频道信息");

        System.out.println("获取频道, id = " + id);
        Channel c = service.getChannel(id);
        if (c != null) {
            return c;
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChannel(@PathVariable String id) {
        System.out.println("即将删除频道, id = " + id);
        boolean result = service.deleteChannel(id);
        if (result) {
            return ResponseEntity.ok().body("删除成功");
        } else {
            return ResponseEntity.ok().body("删除失败");
        }

    }


    @PostMapping
    public Channel createChannel(@RequestBody Channel c) {
        System.out.println("即将新建，频道数据 :" + c);
        Channel saved = service.createChannel(c);
        return saved;
    }

    @PutMapping
    public Channel renewChannel(@RequestBody Channel c) {
        System.out.println("即将更新，频道数据：" + c);
        Channel updated = service.renewChannel(c);
        return null;
    }

    @GetMapping("/q/{quality}")
    public List<Channel> serchByQuality(@PathVariable String quality) {
        return service.serchByQuality(quality);
    }

    @GetMapping("/t/{title}")
    public List<Channel> serchByTitle(@PathVariable String title) {
        return service.serchByTitle(title);
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteChannelAll(String channelId) {
        boolean result = service.deleteChannelAll(channelId);
        if (result) {
            return ResponseEntity.ok().body("删除成功");
        } else {
            return ResponseEntity.ok().body("删除失败");
        }
    }


    @GetMapping("/cold")
    public List<Channel> getColdChannel(){
        return service.findColdChannels();
    }

    @GetMapping("/p/{page}")
    public List<Channel> getChannelsPage(@PathVariable int page){
        return service.findChannelsPage(page);
    }

}
