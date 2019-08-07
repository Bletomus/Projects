#include <stdio.h>
#include <stdlib.h>
#define INF 32000
#define V 4
void printsolu(int dist[V][V])
{
    int i = 0, j = 0;
    for(; i < V; i++)
    {
        for(j= 0;j < V;j++)
        {
            if (dist[i][j] == INF)
                printf("%7s", "INF");
            else
                printf ("%7d", dist[i][j]);
        }
        printf("\n");
    }
}
void floyd(int graph[V][V])
{
    int dist[V][V], i = 0, j = 0, k = 0;
    for(;i < V; i++)
        for(j = 0;j < V;j++)
            dist[i][j] = graph[i][j];

    for(i = 0;i < V; i++)
        for(j = 0;j < V;j++)
            for(k = 0;k < V;k++)
            {
                if (dist[j][i] + dist[i][k] < dist[j][k])
                    dist[j][k] = dist[j][i] + dist[i][k];
            }

    printf("\n\n");
    printsolu(dist);
}

int main()
{
    int graph[V][V] =
    {
        {0,5,INF,10},
        {INF,0,3,INF},
        {INF,INF,0,1},
        {INF,INF,INF,0}
    };
    printsolu(graph);
    floyd(graph);
    return 0;
}
