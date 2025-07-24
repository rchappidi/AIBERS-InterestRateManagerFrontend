FROM nginx:alpine

# Copy custom nginx config
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Copy build output to nginx web root
COPY build /usr/share/nginx/html

EXPOSE 8080
