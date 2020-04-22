package edu.scujcc.service;

import edu.scujcc.dao.ChannelRepository;
import edu.scujcc.model.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChannelService {
    @Autowired
    private ChannelRepository repo;
    private List<Channel> channels;

    public ChannelService(){

    }
    //获取所有频道的数据
    public List<Channel> getAllChannels(){
        return repo.findAll();
    }


//    获取一个频道的数据
//    @param channelId 频道编号
//    @return 频道对象，若未找到则返回null
    public Channel getChannel(String channelId){
        Optional<Channel> result = repo.findById(channelId);
        if (result.isPresent()){
            return result.get();
        }else {
            return null;
        }
    }
     //删除指定频道
    //channleId 待删除的频道id
    public boolean deleteChannel ( String channelId){
        boolean result = true;
        repo.deleteById(channelId);
        return result;
    }

    //保存传入数据
    // c  待保存的频道对象（无id值）
    //
    public Channel createChannel(Channel c){

        return repo.save(c);
    }

    //更新频道信息
    //c 新的频道信息
    public  Channel renewChannel(Channel c){
        //TODO 仅修改用户输入的数据
        Channel saved = getChannel(c.getId());
        if (c.getTitle() != null) {
            saved.setTitle(c.getTitle());
        }

        if (c.getQuality() != null) {
            saved.setQuality(c.getQuality());
        }

        if (c.getUrl() != null) {
            saved.setUrl(c.getUrl());
        }

        // 把新评论追加到老评论后面
        if (c.getComments() != null) {
            if (saved.getComments() != null) {
                saved.getComments().addAll(c.getComments());
            }
            else{//用新评论代替老的空的评论
                    saved.setComments(c.getComments());
                }
            }


        return repo.save(saved);//保存更新后的实体对象

    }


    public List<Channel> serchByQuality(String quality){
        return repo.findByQuality(quality);
    }

    public List<Channel> serchByTitle(String title){
        return repo.findByTitle(title);
    }

    //删除所有频道
    public boolean deleteChannelAll ( String channelId){
        boolean result = true;
        repo.deleteAll();
        return result;
    }


    //获取冷门频道
    public List<Channel> findColdChannels(){
        return repo.findByCommentsNull();
    }

    public List<Channel> findChannelsPage(int page){
        Page<Channel> p = repo.findAll(PageRequest.of(page,3));
        return p.toList();
    }

}
