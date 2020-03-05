function frame_avg = find_frame_avg(vidobj,numFrames)
    for i = 1 : numFrames
        frame = im2double(read(vidobj, i));
        if(i == 1)
            frame_avg = frame;
        else
            frame_avg = (((numFrames-1)/numFrames)*frame_avg) + ((1/numFrames)*frame);
        end
    end
end