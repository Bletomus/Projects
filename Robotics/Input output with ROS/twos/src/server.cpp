#include "ros/ros.h"
#include "twos/twos_msg.h"
#include <iostream>

void messageCallback(const twos::twos_msg::ConstPtr& msg)
{
	std::string sum = "sum";
	std::string diff = "difference";
	std::string product = "product";
	std::string quotient = "div";
	ROS_INFO("Ready to calculate");
	if(sum.compare(msg->lines.c_str()) == 0)
	{
	
		ROS_INFO("Sum: [%f]", (float)(msg->A + msg->B));
		return ;
	}
	else if(diff.compare(msg->lines.c_str()) == 0)
	{
		ROS_INFO("Difference: [%f]", (float)(msg->A - msg->B));
		return ;
	}
	else if(product.compare(msg->lines.c_str()) == 0)
	{
		ROS_INFO("Product: [%f]", (float)(msg->A * msg->B));
		return ;
	}
	else if(quotient.compare(msg->lines.c_str()) == 0)
	{
		
		ROS_INFO("Quotient: [%f]", (float)(msg ->A / msg->B));
		return ;
	}
	else
	{
		ROS_INFO("Dad?");
	}

	return ;
}
int main(int argc, char **argv)
{
	ros::init(argc, argv, "server");
	ros::NodeHandle n;
	ros::Subscriber sub = n.subscribe("message", 1000,messageCallback);
	
	ros::spin();
	return 0;
}
