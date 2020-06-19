#include <ros/ros.h>
#include <dynamic_reconfigure/server.h>
#include "twos/twos_msg.h"
#include <twos/two_Config.h>

ros::Publisher pubs;

void callback(twos::two_Config& config, uint32_t level)
{

	twos::twos_msg msg;
	msg.A = config.num1;
	msg.B = config.num2;
	msg.lines = config.lines.c_str();
	pubs.publish(msg);
	ROS_INFO("Reconfigure Request: %f %f %s",config.num1,config.num2,config.lines.c_str());
	
}

int main(int argc, char **argv) 
{
	ros::init(argc, argv, "client");
	ros::NodeHandle n;
	ros::Publisher pub = n.advertise<twos::twos_msg>("message", 1000);
	ros::Rate loop_rate(10);
	pubs = pub;
	dynamic_reconfigure::Server<twos::two_Config>server; 
	dynamic_reconfigure::Server<twos::two_Config>::CallbackType f;
	f = boost::bind(&callback, _1, _2);
	server.setCallback(f);
	while(ros::ok())
	{
		
		ros::spinOnce();
		loop_rate.sleep();
	}
	

	return 0;
}