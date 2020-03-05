sky_one = VideoReader('.\Test Images\hw1_sky_1.avi');
sky_two = VideoReader('.\Test Images\hw1_sky_2.avi');
%numFrames=get(sky_one,'NumberOfFrames');
numFrames = 30;
sky_one_frame_avg = find_frame_avg(sky_one,numFrames);
sky_two_frame_avg = find_frame_avg(sky_two,numFrames);
figure, imshow(sky_one_frame_avg);
figure, imshow(sky_two_frame_avg);